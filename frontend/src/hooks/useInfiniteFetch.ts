import { useState, useEffect } from 'react';

import { API_URL } from '@/constants';

interface UseFetchProps<T, D> {
  key: keyof D;
  url: string;
  defaultValue: T[];
  offset?: number;
  intersecting: boolean;
}
interface ResponseProps<D> {
  status: number;
  message: string;
  data: D;
}
export function useInfiniteFetch<T, D>({ key, defaultValue, url, offset, intersecting }: UseFetchProps<T, D>) {
  const [data, setData] = useState(defaultValue);
  const [hasNext, setHasNext] = useState(true);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState('');

  async function fetcher() {
    try {
      const response = await fetch(`${API_URL}${url}&offset=${offset}`);
      if (!response.ok) {
        throw new Error(`${response.status} ${response.statusText}`);
      }

      const { data } = (await response.json()) as ResponseProps<D>;

      if (data === null || data === undefined) {
        setHasNext(false);
        return;
      }

      setData(prev => [...prev, ...(data[key] as [])]);
    } catch (error) {
      if (error instanceof Error) {
        setError(error.message);
      } else {
        setError(String(error));
      }
    }
    setIsLoading(false);
  }

  if (error !== '') {
    throw new Error(error);
  }

  useEffect(() => {
    if (intersecting && hasNext) {
      fetcher();
    }
  }, [intersecting]);

  return { data, isLoading, error };
}
