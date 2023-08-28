import { useContext } from 'react';

import { getLocalStorage, setLocalStorage } from '@/utils';
import { CommonError } from '@/utils/CommonError';
import { API_URL } from '@/constants';

import { AuthContext } from '@/AuthProvider';

export function useReissueToken() {
  const { setIsSignin, setUserName } = useContext(AuthContext);

  const refreshToken = getLocalStorage('refreshToken');

  async function tokenFetcher() {
    try {
      const response = await fetch(`${API_URL}/auth/access-token/reissue`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${refreshToken}`,
        },
      });
      const { statusCode, message, data } = await response.json();

      if (!response.ok) {
        throw new CommonError(message, statusCode);
      }

      setLocalStorage('accessToken', data.accessToken);
      setLocalStorage('refreshToken', data.refreshToken);

      setIsSignin(true);
      setUserName(data.userName);
    } catch (error) {
      if (error instanceof CommonError) {
        const { statusCode, message } = error;

        if (statusCode === 401) {
          // 세션 만료 및 사용자에게 재로그인 요청
          setIsSignin(false);
          localStorage.clear();
          throw new CommonError(message, statusCode);
        } else {
          throw new CommonError(message, statusCode);
        }
      } else {
        throw new Error(String(error));
      }
    }
  }

  return { tokenFetcher };
}
