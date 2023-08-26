import { useState, useEffect, MutableRefObject } from 'react';

import { API_URL } from '@/constants';

interface UseFetchProps {
  key: string;
  url: string;
  intersecting: boolean;
  nextOffset: MutableRefObject<number>;
  dependencies?: string[];
}
interface ResponseProps<T> {
  status: number;
  message: string;
  data: T;
}

export function useInfiniteFetch<T>({ key, url, intersecting, nextOffset, dependencies }: UseFetchProps) {
  interface Props {
    key: T[];
    nextOffset: number;
    [key: string]: any;
  }

  const [data, setData] = useState<T[]>([]);
  const [hasNext, setHasNext] = useState(true);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState('');

  async function fetcher() {
    try {
      const response = await fetch(`${API_URL}${url}`);
      if (!response.ok) {
        throw new Error(`${response.status} ${response.statusText}`);
      }

      const { data } = (await response.json()) as ResponseProps<Props>;

      if (data.archivings.length === 0) {
        setHasNext(false);
        setIsLoading(false);
        return;
      }

      if (data.nextOffset === null) {
        setHasNext(false);
        return;
      }
      nextOffset.current = data.nextOffset;
      setData(prev => [...prev, ...(data[key] as [])]);
      setIsLoading(false);
    } catch (error) {
      if (error instanceof Error) {
        setError(error.message);
      } else {
        setError(String(error));
      }
    }
  }

  if (error !== '') {
    throw new Error(error);
  }

  const dependenciesString = JSON.stringify(dependencies);

  useEffect(() => {
    setData([]);
    setHasNext(true);
    nextOffset.current = 1;

    if (intersecting) {
      setIsLoading(true);

      window.scroll({
        top: 0,
        behavior: 'smooth',
      });

      fetcher();
    }
  }, [dependenciesString]);

  useEffect(() => {
    if (intersecting && hasNext) {
      setIsLoading(true);
      window.scroll({
        top: 0,
        behavior: 'smooth',
      });
      fetcher();

      return;
    }
  }, [intersecting]);

  return { data, isLoading, error, hasNext };
}
