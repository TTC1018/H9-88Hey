import { useEffect, useState } from 'react';

import { AuthError } from '@/utils/AuthError';

export function useErrorHandler() {
  const [errorState, setErrorState] = useState(null);

  function handleError(error: any) {
    if (error !== '') {
      setErrorState(error);
    }
  }

  useEffect(() => {
    if (errorState !== null) {
      const { message, statusCode } = errorState;
      throw new AuthError(message, statusCode);
    }
  }, [errorState]);

  return { handleError };
}
