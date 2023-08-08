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
  const [error, setError] = useState<unknown>(null);

  async function fetchUsers() {
    try {
      const response = await fetch(url);

      if (!response.ok) {
        throw new Error(`${response.status} ${response.statusText}`);
      }

      const { data } = (await response.json()) as ResponseType<T>;
      // status 관련 로직 작성

      setData(data);
    } catch (error: unknown) {
      setError(error);
    }
    setIsLoading(false);
  }

  useEffect(() => {
    fetchUsers();
  }, []);

  return { data, isLoading, error };
}
