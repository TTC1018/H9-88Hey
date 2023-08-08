import { Dispatch, ReactNode, SetStateAction, createContext, useState } from 'react';

import { ModalTypeProps } from '@/constants';

const DEFAULT_VALUE = { modalType: ModalTypeProps.CLOSE, callbackData: null };

interface StateProps {
  modalType: ModalTypeProps;
  ContentData?: string;
  // 나중에 정해지면 수정
  callbackData: any;
}
interface ModalContextProps {
  isOpen: boolean;
  handleClose: () => void;
  handleOpen: () => void;
  modalState: StateProps;
  setModalState: Dispatch<SetStateAction<StateProps>>;
}
export const ModalContext = createContext<ModalContextProps>({
  isOpen: false,
  handleClose: () => {},
  handleOpen: () => {},
  modalState: DEFAULT_VALUE,
  setModalState: () => {},
});

interface Props {
  children: ReactNode;
}
export function ModalProvider({ children }: Props) {
  const [modalState, setModalState] = useState<StateProps>(DEFAULT_VALUE);

  const [isOpen, setIsOpen] = useState(false);

  function handleClose() {
    setIsOpen(false);
  }

  function handleOpen() {
    setIsOpen(true);
  }

  return (
    <ModalContext.Provider value={{ isOpen, handleClose, handleOpen, modalState, setModalState }}>
      {children}
    </ModalContext.Provider>
  );
}
