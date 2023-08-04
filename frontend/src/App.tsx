import { RouterProvider } from 'react-router';
import { ThemeProvider } from '@emotion/react';

import { router } from './routes/router';
import { theme } from './styles/theme';
import { GlobalStyle } from './styles/GlobalStyle';

export function App() {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <RouterProvider router={router} />
    </ThemeProvider>
  );
}
