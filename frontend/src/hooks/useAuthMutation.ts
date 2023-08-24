import { useState } from 'react';

import { useValidateToken } from './useValidateToken';
import { useReissueToken } from './useReissueToken';
import { API_URL } from '@/constants';
import { AuthError } from '@/utils/AuthError';
import { getLocalStorage } from '@/utils';

interface Props {
  url: string;
}
interface ResponseProps<T> {
  statusCode: number;
  message: string;
  data: T;
}

export function useAuthMutation<T, U>({ url }: Props) {
  const [data, setData] = useState<T>();
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState('');

  const { tokenValidator } = useValidateToken();
  const { tokenFetcher } = useReissueToken();

  async function fetcher({
    shouldReissueToken,
    method,
    body,
  }: {
    shouldReissueToken: boolean;
    method: string;
    body?: U;
  }) {
    const accessToken = getLocalStorage('accessToken');
    try {
      const response = await fetch(`${API_URL}${url}`, {
        method,
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${accessToken}`,
        },
        body: JSON.stringify(body),
      });

      const { statusCode, message, data } = (await response.json()) as ResponseProps<T>;

      if (!response.ok) {
        throw new AuthError(message, statusCode);
      }

      setData(data);
    } catch (error) {
      if (error instanceof AuthError) {
        const { statusCode, message } = error;

        if (statusCode === 401) {
          if (shouldReissueToken) {
            try {
              await tokenFetcher();
              fetcher({ shouldReissueToken, method, body });
            } catch (error) {
              // tokenFetcher에서 세션 만료 예외 처리됨
              return;
            }
          } else {
            // 재발급받은 토큰으로 한번 더 401 에러가 뜨는 시나리오
            // 일어날 일 없음
            return;
          }
        } else {
          setError(message);
        }
      } else {
        setError(String(error));
      }
    }
  }

  interface PostProps {
    method: string;
    data?: U;
  }
  async function authMutation({ method, data }: PostProps) {
    // 1. 액세스 토큰과 리프레시 토큰이 비어있는 경우 로그아웃 상태

    // 2. 액세스 토큰이 있는 경우 fetcher 함수를 통해 액세스 토큰 유효성을 검증
    // 2.1. 200인 경우 -> 그대로 진행
    // 2.2. 401인 경우 -> 액세스 토큰과 리프레시 토큰을 재발급 -> 거기서도 401인 경우(리프레시 토큰이 만료되었거나 리프레시 토큰이 없는 경우 포함) -> 재로그인 요청
    try {
      await tokenValidator(true);
    } catch (error) {
      await tokenFetcher();
    }

    await fetcher({ shouldReissueToken: true, method, body: data });

    setIsLoading(false);
  }

  if (error !== '') {
    throw new Error(error);
  }

  return { authMutation, data, isLoading, error };
}
