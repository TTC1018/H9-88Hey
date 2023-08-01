import { ThemeProvider } from '@emotion/react';
import { GlobalStyle } from './styles/GlobalStyle';
import { theme } from './styles/theme';
import { RouterProvider } from 'react-router';
import { router } from './routes/router';

export function App() {
  return (
    <>
      <ThemeProvider theme={theme}>
        <GlobalStyle />
        <RouterProvider router={router} />
      </ThemeProvider>
    </>
  );
}
