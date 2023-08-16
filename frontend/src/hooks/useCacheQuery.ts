import { useState, useEffect, useContext } from 'react';

import { API_URL } from '@/constants';

import { CacheContext } from '@/CacheProvider';

interface UseQueryProps<T> {
  defaultValue: T;
  url: string;
  stateTime: number;
  key: string;
}
interface ResponseProps<T> {
  status: number;
  message: string;
  data: T;
}

function checkIsStale(dataUpdatedAt: Date, staleTime: number) {
  const currentTime = Date.now();
  const lastUpdatedTime = new Date(dataUpdatedAt).getTime();

  return currentTime - lastUpdatedTime < staleTime;
}

export function useCacheQuery<T>({ defaultValue, url, stateTime, key }: UseQueryProps<T>) {
  const { cachedDatas } = useContext(CacheContext);
  const cachedData = cachedDatas.get(key);

  const [data, setData] = useState(defaultValue);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState('');

  async function fetcher() {
    try {
      if (cachedData) {
        const { data, dataUpdatedAt } = cachedData;
        if (checkIsStale(dataUpdatedAt, stateTime)) {
          if (cachedData) {
            setData(data);

            return;
          }
        }
      }
      const response = await fetch(`${API_URL}${url}`);

      if (!response.ok) {
        throw new Error(`${response.status} ${response.statusText}`);
      }

      const { data } = (await response.json()) as ResponseProps<T>;

      setData(data);
      cachedDatas.set(key, { data: data, dataUpdatedAt: new Date() });
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
