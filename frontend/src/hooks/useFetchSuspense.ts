import { useEffect, useRef } from 'react';

import { useCache } from './useCache';

interface Props<T> {
  fetcher: () => Promise<ResponseProps<T>>;
  key: string[];
}
interface ResponseProps<T> {
  status: number;
  message: string;
  data: T;
}

export function useFetchSuspense<T>({ fetcher, key }: Props<T>) {
  const promise = useRef<Promise<ResponseProps<any>>>();
  const { getCache, setCache } = useCache();
  const value = getCache({ key }) || { status: 'new', data: null, dataUpdatedAt: new Date() };

  if (value.status === 'resolved') {
    return value.data as T;
  }

  const fetchData = () => {
    promise.current = (async () => {
      await new Promise(resolve => {
        setTimeout(resolve, 500);
      });

      return fetcher();
    })();

    promise.current
      .then(response => {
        setCache({
          key,
          value: {
            status: 'resolved',
            data: response.data,
            dataUpdatedAt: new Date(),
          },
        });
      })
      .catch(error => {
        throw new Error(error);
      });

    throw promise.current;
  };

  fetchData();

  useEffect(() => {
    fetchData();
  }, []);

  throw promise.current;
}
