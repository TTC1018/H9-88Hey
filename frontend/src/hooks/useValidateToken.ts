import { useContext } from 'react';

import { getLocalStorage, removeLocalStorage } from '@/utils';
import { AuthError } from '@/utils/AuthError';
import { API_URL } from '@/constants';

import { AuthContext } from '@/AuthProvider';

export function useValidateToken() {
  const { setIsSignin, setUserName } = useContext(AuthContext);

  const accessToken = getLocalStorage('accessToken');
  const refreshToken = getLocalStorage('refreshToken');

  async function tokenValidator() {
    if (accessToken === null && refreshToken === null) {
      console.log('No token found');
      return;
    }

    try {
      const response = await fetch(`${API_URL}/auth/access-token/validate`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${accessToken}`,
        },
      });

      const { statusCode, message, data } = await response.json();

      if (!response.ok) {
        throw new AuthError(message, statusCode);
      }

      console.log('액세스 토큰 검증 성공');

      setIsSignin(true);
      setUserName(data.userName);
    } catch (error) {
      if (error instanceof AuthError) {
        const { statusCode, message } = error;

        console.log('액세스 코드 검증 실패! 재발급을 요청합니다.');

        setIsSignin(false);
        removeLocalStorage('accessToken');
        removeLocalStorage('refreshToken');

        throw new AuthError(message, statusCode);
      } else {
        throw new Error(String(error));
      }
    }
  }

  return { tokenValidator };
}
