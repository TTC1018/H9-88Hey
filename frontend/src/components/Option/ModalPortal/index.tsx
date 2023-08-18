import { MouseEvent, ReactNode, useRef } from 'react';

import ReactDom from 'react-dom';

import * as Styled from './style';

interface ModalPortalProps {
  children: ReactNode;
  isOpen: boolean;
  handleClose: () => void;
}
export const ModalPortal = ({ children, isOpen, handleClose }: ModalPortalProps) => {
  const modalRef = useRef<HTMLDivElement>(null);
  const element = document.getElementById('modal') as HTMLElement;

  function handleOutsideClick({ target }: MouseEvent<HTMLDivElement>) {
    if (modalRef.current && !modalRef.current.contains(target as Node)) {
      handleClose();
    }
  }

  return isOpen
    ? ReactDom.createPortal(
        <Styled.Container onClick={handleOutsideClick}>
          <div ref={modalRef}>{children}</div>
        </Styled.Container>,
        element
      )
    : null;
};
