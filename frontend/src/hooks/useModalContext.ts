import { ModalContext } from '@/components/common/ModalProvider';
import { useContext } from 'react';

export function useModalContext() {
  return useContext(ModalContext);
}
