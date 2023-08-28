import { useRef } from 'react';

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

  if (value.status === 'rejected') {
    throw new Error(value.status);
  }
  if (value.status === 'resolved') {
    return value.data as T;
  }

  function fetchData() {
    promise.current = (async () => {
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
        setCache({
          key,
          value: {
            status: 'rejected',
            data: null,
            dataUpdatedAt: new Date(),
          },
        });
        throw new Error(error);
      });

    throw promise.current;
  }

  fetchData();

  throw promise.current;
}
