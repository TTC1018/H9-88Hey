import { RouterProvider } from 'react-router';
import { ThemeProvider } from '@emotion/react';

import { ModalProvider } from './components/common/ModalProvider';

import { router } from './routes/router';
import { theme } from './styles/theme';
import { GlobalStyle } from './styles/globalStyle';

export function App() {
  return (
    <>
      <ThemeProvider theme={theme}>
        <GlobalStyle />
        <ModalProvider>
          <RouterProvider router={router} />
        </ModalProvider>
      </ThemeProvider>
    </>
  );
}
