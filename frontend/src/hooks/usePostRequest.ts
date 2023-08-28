import { useState } from 'react';

import { API_URL } from '@/constants';
import { getLocalStorage } from '@/utils';
import { CommonError } from '@/utils/CommonError';

interface Props {
  url: string;
}
interface ResponseProps<T> {
  status: number;
  message: string;
  data: T;
}
export function usePostRequest<T, U>({ url }: Props) {
  const [data, setData] = useState<T>();
  const [isSuccess, setIsSuccess] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);

  interface PostProps {
    method: string;
    data?: U;
  }
  async function postData({ method, data }: PostProps) {
    setIsLoading(true);
    setError(null);
    const accessToken = getLocalStorage('accessToken');
    try {
      const response = await fetch(`${API_URL}${url}`, {
        method,
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${accessToken}`,
        },
        body: JSON.stringify(data),
      });

      if (!response.ok) {
        throw new CommonError('네트워크가 원활하지 않습니다.', 500);
      }

      const { data: responseData } = (await response.json()) as ResponseProps<T>;
      setData(responseData);
      setIsLoading(false);
      setIsSuccess(true);
    } catch (error: any) {
      setError(error);
      setIsLoading(false);
      setIsSuccess(false);
    }
  }

  return { postData, data, isSuccess, isLoading, error };
}
