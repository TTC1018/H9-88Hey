import { useContext } from 'react';
import { RouterProvider } from 'react-router';
import { ThemeProvider } from '@emotion/react';

import { useValidateToken } from './hooks/useValidateToken';
import { useReissueToken } from './hooks/useReissueToken';

import { ModalProvider } from './components/common/ModalProvider';
import { CacheProvider } from './CacheProvider';
import { router } from './routes/router';
import { AuthContext } from './AuthProvider';

import { theme } from './styles/theme';
import { GlobalStyle } from './styles/globalStyle';

export function App() {
  const { tokenValidator } = useValidateToken();
  const { tokenFetcher } = useReissueToken();

  const { isSignin } = useContext(AuthContext);

  async function validateSignin() {
    try {
      await tokenValidator();
    } catch (error) {
      await tokenFetcher();
    }
  }

  if (!isSignin) {
    validateSignin();
  }

  return (
    <>
      <ThemeProvider theme={theme}>
        <GlobalStyle />
        <CacheProvider>
          <ModalProvider>
            <RouterProvider router={router} />
          </ModalProvider>
        </CacheProvider>
      </ThemeProvider>
    </>
  );
}
