import { ReactNode, useEffect } from 'react';
import { createPortal } from 'react-dom';

interface Props {
  children: ReactNode;
}

export function OptionModalProvider({ children }: Props) {
  const optionModalRoot = document.querySelector('#option-modal-root');
  const element = document.createElement('div');

  useEffect(() => {
    optionModalRoot?.appendChild(element);
    return () => {
      optionModalRoot?.removeChild(element);
    };
  }, [element, optionModalRoot]);

  return createPortal(children, element);
}
