import { API_URL } from '@/constants';

interface Props {
  url: string;
  fetchOptions?: RequestInit;
}
interface ResponseProps<T> {
  status: number;
  message: string;
  data: T;
}

export function mutation<T>({ url, fetchOptions }: Props): Promise<ResponseProps<T>> {
  return fetch(`${API_URL}${url}`, fetchOptions).then(response => response.json());
}
