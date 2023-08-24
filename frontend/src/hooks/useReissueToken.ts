import { useContext } from 'react';

import { getLocalStorage, removeLocalStorage, setLocalStorage } from '@/utils';
import { AuthError } from '@/utils/AuthError';
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
        throw new AuthError(message, statusCode);
      }

      console.log('액세스 토큰 및 리프레시 토큰이 성공적으로 재발급되었습니다.');

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
          removeLocalStorage('accessToken');
          removeLocalStorage('refreshToken');

          // TODO: 세션이 만료되었습니다. 다시 로그인 해주세요. 모달 표시
          console.log('세션이 만료되었습니다. 다시 로그인 해주세요.');
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
