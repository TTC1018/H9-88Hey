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

    console.log('access token valid test', accessToken);
    console.log('refresh token valid test', refreshToken);

    if (accessToken === null && refreshToken === null) {
      console.log('logout state then tokenFetcher finished');
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

      console.log('access token reissue completed', data.accessToken);
    } catch (error) {
      if (error instanceof AuthError) {
        const { statusCode, message } = error;

        if (statusCode === 401) {
          // clear tokens
          setIsSignin(false);
          localStorage.removeItem('accessToken');
          localStorage.removeItem('refreshToken');

          // TODO: 세션이 만료되었습니다. 다시 로그인 해주세요. 모달 표시
          console.log('세션이 만료되었습니다. 다시 로그인 해주세요.');
          console.log(error);
          throw error;
        } else {
          console.log('예상치 못한 에러 발생 ㄷㄷ', statusCode, message);
        }
      } else {
        throw error;
      }
    }
  }

  return { tokenFetcher };
}
