import { useEffect, useState } from 'react';

import { CommonError } from '@/utils/CommonError';

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
      throw new CommonError(message, statusCode);
    }
  }, [errorState]);

  return { handleError };
}
