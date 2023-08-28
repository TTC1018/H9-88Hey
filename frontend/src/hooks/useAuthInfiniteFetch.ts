import { useState, useEffect, MutableRefObject } from 'react';

import { API_URL } from '@/constants';
import { useValidateToken } from './useValidateToken';
import { useReissueToken } from './useReissueToken';
import { getLocalStorage } from '@/utils';
import { CommonError } from '@/utils/CommonError';

interface UseAuthFetchProps {
  key: string;
  url: string;
  intersecting: boolean;
  nextOffset: MutableRefObject<number>;
  method: 'GET' | 'POST' | 'DELELTE';
}

interface ResponseProps<T> {
  status: number;
  message: string;
  data: T;
}

export function useAuthInfiniteFetch<T>({ key, url, intersecting, nextOffset, method }: UseAuthFetchProps) {
  interface Props {
    key: T[];
    nextOffset: number;
    [key: string]: any;
  }

  const [data, setData] = useState<T[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [hasNext, setHasNext] = useState(true);
  const [error, setError] = useState('');

  function handleDelete(id: string, key: 'myChivingId' | 'feedId') {
    // 마이카이빙 내 차 목록 또는 피드 삭제하기
    const newData = data.filter((prev: T) => prev[key as keyof typeof prev] !== id);
    setData(newData);
  }

  const { tokenValidator } = useValidateToken();
  const { tokenFetcher } = useReissueToken();

  async function fetcher(shouldReissueToken: boolean) {
    const accessToken = getLocalStorage('accessToken');
    try {
      const response = await fetch(`${API_URL}${url}`, {
        method,
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${accessToken}`,
        },
      });

      if (!response.ok) {
        throw new Error(`${response.status} ${response.statusText}`);
      }

      const { data } = (await response.json()) as ResponseProps<Props>;

      if (data[key].length === 0) {
        setHasNext(false);
        setIsLoading(false);
        return;
      }

      if (data.nextOffset === null) {
        setHasNext(false);
      }

      nextOffset.current = data.nextOffset;
      setData(prev => [...prev, ...(data[key] as [])]);
    } catch (error) {
      if (error instanceof CommonError) {
        const { statusCode, message } = error;

        if (statusCode === 401) {
          if (shouldReissueToken) {
            try {
              await tokenFetcher();
              fetcher(false);
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

  async function authFetcher() {
    // 1. 액세스 토큰과 리프레시 토큰이 비어있는 경우 로그아웃 상태

    // 2. 액세스 토큰이 있는 경우 fetcher 함수를 통해 액세스 토큰 유효성을 검증
    // 2.1. 200인 경우 -> 그대로 진행
    // 2.2. 401인 경우 -> 액세스 토큰과 리프레시 토큰을 재발급 -> 거기서도 401인 경우(리프레시 토큰이 만료되었거나 리프레시 토큰이 없는 경우 포함) -> 재로그인 요청
    try {
      await tokenValidator();
    } catch (error) {
      await tokenFetcher();
    }

    await fetcher(true);
    setIsLoading(false);
  }

  if (error !== '') {
    throw new Error(error);
  }

  useEffect(() => {
    if (intersecting && hasNext) {
      setIsLoading(true);

      authFetcher();

      return;
    }
  }, [intersecting]);

  return { data, isLoading, error, handleDelete };
}
