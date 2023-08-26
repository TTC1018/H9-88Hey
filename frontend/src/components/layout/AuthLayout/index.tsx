import { AuthContext } from '@/AuthProvider';
import { useErrorHandler } from '@/hooks/useErrorHandler';
import { useReissueToken } from '@/hooks/useReissueToken';
import { useValidateToken } from '@/hooks/useValidateToken';
import { useContext, useState } from 'react';
import { Outlet } from 'react-router-dom';

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
        throw new Error('Error');
      }
    }
  }
  async function someComponentFunction() {
    if (!isSignin) {
      try {
        await validateSignin(); // 비동기적으로 호출
      } catch (error) {
        handleError(error);
      }
    }
  }
  someComponentFunction();
  return (
    <>
      <Outlet />
    </>
  );
}
