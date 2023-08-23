import { useContext } from 'react';

import { getLocalStorage, setLocalStorage } from '@/utils';
import { AuthError } from '@/utils/AuthError';
import { API_URL } from '@/constants';

import { AuthContext } from '@/AuthProvider';

export function useReissueToken() {
  const { setIsSignin, setUserName } = useContext(AuthContext);

  async function tokenFetcher() {
    const accessToken = getLocalStorage('accessToken');
    const refreshToken = getLocalStorage('refreshToken');

    if (accessToken === null && refreshToken === null) {
      return;
    }

    try {
      const response = await fetch(`${API_URL}/auth/access-token`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${refreshToken}`,
        },
      });
      const { statusCode, message, data } = await response.json();

      if (!response.ok) {
        throw new AuthError(message, statusCode);
      }

      setLocalStorage('accessToken', data.accessToken);
      setLocalStorage('refreshToken', data.refreshToken);

      setIsSignin(true);
      setUserName(data.userName);
    } catch (error) {
      if (error instanceof AuthError) {
        const { statusCode, message } = error;

        if (statusCode === 401) {
          // 토큰 초기화
          setIsSignin(false);
          localStorage.removeItem('accessToken');
          localStorage.removeItem('refreshToken');

          // TODO: 세션이 만료되었습니다. 다시 로그인 해주세요. 모달 표시
          throw new Error(message);
        } else {
          throw new Error(message);
        }
      } else {
        throw new Error(String(error));
      }
    }
  }

  return { tokenFetcher };
}
