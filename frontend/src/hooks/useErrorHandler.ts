import { useEffect, useState } from 'react';

export function useErrorHandler() {
  const [errorState, setErrorState] = useState(null);

  function handleError(error: any) {
    if (error !== '') {
      setErrorState(error);
    }
  }

  useEffect(() => {
    if (errorState !== null) {
      throw new Error(errorState);
    }
  }, [errorState]);

  return { handleError };
}
