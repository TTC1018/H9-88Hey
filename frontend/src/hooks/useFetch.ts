import { API_Url } from '@/constants';
import { useState, useEffect } from 'react';

interface UseFetchProps<T> {
  defaultValue: T;
  url: string;
}
interface ResponseType<T> {
  status: number;
  message: string;
  data: T;
}
export function useFetch<T>({ defaultValue, url }: UseFetchProps<T>) {
  const [data, setData] = useState(defaultValue);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState('');

  async function fetchUsers() {
    try {
      const response = await fetch(`${API_Url}${url}`);

      if (!response.ok) {
        throw new Error(`${response.status} ${response.statusText}`);
      }

      const { data } = (await response.json()) as ResponseType<T>;

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
    fetchUsers();
  }, []);

  return { data, isLoading, error };
}
