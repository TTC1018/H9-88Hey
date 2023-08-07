import { MouseEvent, ReactNode, useRef } from 'react';

import ReactDom from 'react-dom';

import * as style from './style';

interface ModalPortalProps {
  children: ReactNode;
  isOpen: boolean;
  onClose: () => void;
}

export const ModalPortal = ({ children, isOpen, onClose }: ModalPortalProps) => {
  const modalRef = useRef<HTMLDivElement>(null);
  const el = document.getElementById('modal') as HTMLElement;

  const handleOutsideClick = (e: React.MouseEvent<HTMLDivElement>) => {
    if (modalRef.current && !modalRef.current.contains(e.target as Node)) {
      onClose();
    }
  };

  return isOpen
    ? ReactDom.createPortal(
        <style.Container onClick={handleOutsideClick}>
          <div ref={modalRef}>{children}</div>
        </style.Container>,
        el
      )
    : null;
};
