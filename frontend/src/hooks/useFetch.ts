import { useState, useEffect } from 'react';

import { API_URL } from '@/constants';
import { getLocalStorage } from '@/utils';

interface UseFetchProps<T> {
  defaultValue: T;
  url: string;
}
interface ResponseProps<T> {
  status: number;
  message: string;
  data: T;
}
export function useFetch<T>({ defaultValue, url }: UseFetchProps<T>) {
  const [data, setData] = useState(defaultValue);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState('');

  async function fetcher() {
    const accessToken = getLocalStorage('accessToken');
    try {
      const response = await fetch(`${API_URL}${url}`, {
        headers: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
      if (!response.ok) {
        throw new Error(`${response.status} ${response.statusText}`);
      }

      const { data } = (await response.json()) as ResponseProps<T>;

      setData(data);
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
    fetcher();
  }, []);

  return { data, isLoading, error };
}
