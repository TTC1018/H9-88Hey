import { ThemeProvider } from '@emotion/react';
import GlobalStyle from './styles/globalStyle';
import { theme } from './styles/theme';

export function App() {
  return (
    <>
      <ThemeProvider theme={theme}>
        <GlobalStyle />
      </ThemeProvider>
    </>
  );
}
