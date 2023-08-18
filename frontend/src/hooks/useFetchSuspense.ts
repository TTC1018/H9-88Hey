import { API_URL } from '@/constants';
import { useCache } from './useCache';

interface Props {
  url: string;
  fetchOptions?: RequestInit;
  key: string[];
  staleTime: number;
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

export function useFetchSuspense<T>({ url, fetchOptions = {}, key, staleTime }: Props) {
  const { getCache, setCache } = useCache();

  const value = getCache({ key }) || { status: 'new', data: null, dataUpdatedAt: new Date() };

  if (value.status === 'resolved') {
    if (checkIsStale(value.dataUpdatedAt, staleTime)) {
      return value.data as T;
    }
  }

  const promise: Promise<ResponseProps<T>> = fetch(`${API_URL}${url}`, fetchOptions).then(response => response.json());

  promise
    .then(data => {
      setTimeout(() => {
        setCache({
          key,
          value: {
            status: 'resolved',
            data: data.data,
            dataUpdatedAt: new Date(),
          },
        });
      }, 1000);
    })
    .catch(error => {
      throw new Error(error);
    });

  throw promise;
}
