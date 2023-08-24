import { useState } from 'react';

import { API_URL } from '@/constants';

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

    try {
      const response = await fetch(`${API_URL}${url}`, {
        method,
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ`,
        },
        body: JSON.stringify(data),
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
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
