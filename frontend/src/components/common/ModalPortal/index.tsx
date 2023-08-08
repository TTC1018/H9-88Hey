import { ReactNode, useContext, useRef } from 'react';

import ReactDom from 'react-dom';

import { ModalContext } from '@/components/common/ModalProvider';

import * as style from './style';

interface ModalPortalProps {
  children: ReactNode;
}
export const ModalPortal = ({ children }: ModalPortalProps) => {
  const { isOpen, handleClose } = useContext(ModalContext);

  const modalRef = useRef<HTMLDivElement>(null);
  const el = document.getElementById('modal') as HTMLElement;

  const handleOutsideClick = (e: React.MouseEvent<HTMLDivElement>) => {
    if (modalRef.current && !modalRef.current.contains(e.target as Node)) {
      handleClose();
    }
  };

  return (
    isOpen &&
    ReactDom.createPortal(
      <style.Container onClick={handleOutsideClick}>
        <div ref={modalRef}>{children}</div>
      </style.Container>,
      el
    )
  );
};
