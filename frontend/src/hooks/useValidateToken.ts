import { useContext } from 'react';

import { getLocalStorage } from '@/utils';
import { AuthError } from '@/utils/AuthError';
import { API_URL } from '@/constants';

import { AuthContext } from '@/AuthProvider';

export function useValidateToken() {
  const { setIsSignin, setUserName } = useContext(AuthContext);

  const accessToken = getLocalStorage('accessToken');
  const refreshToken = getLocalStorage('refreshToken');

  async function tokenValidator() {
    if (accessToken === null && refreshToken === null) {
      throw new AuthError('토큰이 없습니다.', 401);
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

      setIsSignin(true);
      setUserName(data.userName);
    } catch (error) {
      if (error instanceof AuthError) {
        const { statusCode, message } = error;

        if (statusCode === 401) {
          setIsSignin(false);
          localStorage.clear();
          throw new AuthError(message, statusCode);
        } else {
          throw new AuthError(message, statusCode);
        }
      } else {
        throw new Error(String(error));
      }
    }
  }

  return { tokenValidator };
}
