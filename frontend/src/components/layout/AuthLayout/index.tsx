import { useContext } from 'react';

import { Outlet } from 'react-router-dom';

import { useErrorHandler } from '@/hooks/useErrorHandler';
import { useReissueToken } from '@/hooks/useReissueToken';
import { useValidateToken } from '@/hooks/useValidateToken';
import { CommonError } from '@/utils/CommonError';

import { AuthContext } from '@/AuthProvider';

export function AuthLayout() {
  const { tokenValidator } = useValidateToken();
  const { tokenFetcher } = useReissueToken();

  const { handleError } = useErrorHandler();

  const { isSignin } = useContext(AuthContext);

  async function validateSignin() {
    try {
      await tokenValidator();
    } catch (error) {
      try {
        await tokenFetcher();
      } catch (error) {
        if (error instanceof CommonError) {
          const { message, statusCode } = error;
          throw new CommonError(message, statusCode);
        } else {
          throw new Error(String(error));
        }
      }
    }
  }
  async function handleSignin() {
    if (!isSignin) {
      try {
        await validateSignin();
      } catch (error) {
        handleError(error);
      }
    }
  }
  handleSignin();
  return (
    <>
      <Outlet />
    </>
  );
}
