import { useState, useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom';

import { API_URL } from '@/constants';
import { getLocalStorage, setLocalStorage } from '@/utils';
import { AuthError } from '@/utils/AuthError';

import { AuthContext } from '@/AuthProvider';
import { useFetch } from './useFetch';

interface UseAuthFetchProps<T> {
  defaultValue: T;
  url: string;
}

interface ResponseProps<T> {
  statusCode: number;
  message: string;
  data: T;
}

export function useAuthFetch<T>({ defaultValue, url }: UseAuthFetchProps<T>) {
  const [data, setData] = useState(defaultValue);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState('');

  const { isSignin, setIsSignin, accessToken, setAccessToken, setUserName } = useContext(AuthContext);

  const navigate = useNavigate();

  async function authFetcher() {
    try {
      if (!isSignin) {
        throw new AuthError('로그인 상태가 아닙니다.', 400);
      }

      const response = await fetch(`${API_URL}${url}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${accessToken}`,
        },
      });

      const { statusCode, message, data }: ResponseProps<T> = await response.json();

      if (!response.ok) {
        throw new AuthError(message, statusCode);
      }

      setData(data);
    } catch (error) {
      if (error instanceof AuthError) {
        const { statusCode } = error;

        if (statusCode === 400 || statusCode === 401) {
          await requestToken();
          // catch문에 왔다는 것은 현재 data가 undefined라는 것
          // 따라서 setData(data)를 쓰면은 안되고
          // 여기서 pending된 promise를 다시 처리한다
          const { data } = useFetch({ defaultValue, url });
          setData(data);
        } else {
          setError(error.message);
        }
      } else {
        setError(String(error));
      }
    }

    setIsLoading(false);
  }

  async function requestToken() {
    const localRefreshToken = getLocalStorage('refreshToken');

    try {
      const response = await fetch(`${API_URL}/auth/refresh`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${localRefreshToken}`,
        },
      });

      const { statusCode, message, data } = await response.json();

      if (!response.ok) {
        throw new AuthError(message, statusCode);
      }

      const { accessToken, refreshToken, userName } = data;

      setIsSignin(true);
      setAccessToken(accessToken);
      setLocalStorage('refreshToken', refreshToken);
      setUserName(userName);
    } catch (error) {
      if (error instanceof AuthError) {
        const { statusCode } = error;

        if (statusCode === 401) {
          // clear access token and refresh token
          setAccessToken('');
          setLocalStorage('refreshToken', '');

          navigate('/signin');

          // TEST
          // navigate되었다면 아래의 코드가 실행되지 않아아함
          console.log('navigated to signin page but reached after navigate function!!!');
          console.log('이 문구가 발견되었다면 requestToken 함수에 return을 넣어주세요');
        }
      }
    }
  }

  if (error !== '') {
    throw new Error(error);
  }

  useEffect(() => {
    authFetcher();
  }, []);

  return { data, isLoading, error };
}
