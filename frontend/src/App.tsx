import { useState } from 'react';

import { RouterProvider } from 'react-router';
import { ThemeProvider } from '@emotion/react';

import { ModalPortal } from './components/common/ModalPortal';
import { PopupModal } from './components/common/PopupModal';

import { router } from './routes/router';
import { theme } from './styles/theme';
import { GlobalStyle } from './styles/globalStyle';

export function App() {
  const [isOpen, setIsOpen] = useState(false);

  const handleClose = () => {
    setIsOpen(false);
  };

  return (
    <>
      <ThemeProvider theme={theme}>
        <GlobalStyle />
        <RouterProvider router={router} />
        <ModalPortal isOpen={isOpen} onClose={handleClose}>
          <PopupModal type={'close'} onCancel={handleClose} onConfirm={() => {}} />
        </ModalPortal>
      </ThemeProvider>
    </>
  );
}
