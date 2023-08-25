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
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState('');

  async function fetcher() {
    try {
      const response = await fetch(`${API_URL}${url}`, {
        headers: {
          Authorization: `Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ`,
          credentials: 'same-origin',
        },
      });
      if (!response.ok) {
        throw new Error(`${response.status} ${response.statusText}`);
      }

      const { data } = (await response.json()) as ResponseProps<Props>;

      if (data[key].length === 0) {
        setIsLoading(false);
        return;
      }
      nextOffset.current = data.nextOffset;
      setData(prev => [...prev, ...(data[key] as [])]);
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
    nextOffset.current = 1;

    if (intersecting) {
      setIsLoading(true);
      fetcher();
    }
  }, [dependenciesString]);

  useEffect(() => {
    if (intersecting && nextOffset.current !== null) {
      setIsLoading(true);
      fetcher();

      return;
    }
  }, [intersecting]);

  return { data, isLoading, error };
}
