import { ReactNode } from 'react';
import { createPortal } from 'react-dom';

interface Props {
  children: ReactNode;
}

export function OptionModalProvider({ children }: Props) {
  const optionModalRoot = document.querySelector('#option-modal-root');
  return createPortal(children, optionModalRoot!);
}
