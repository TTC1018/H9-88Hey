import { useState } from 'react';

import { useOutletContext } from 'react-router-dom';

import { MyCarLayoutContextProps } from '@/types/trim';
import { ModalType, MyCarActionType } from '@/constants';

import { PopupModal } from '@/components/common/PopupModal';
import { ModalPortal } from '@/components/Option/ModalPortal';

import * as Styled from './style';

interface TrimCardPropsWithEngineInfo {
  hasEngineInfo: true;
  power: string;
  torque: string;
}
interface TrimCardPropsWithoutEngineInfo {
  hasEngineInfo: false;
  power?: never;
  torque?: never;
}
interface Common {
  title: string;
  price: number;
  isActive: boolean;
  description: string;
  id: number;
  dispatchKey: 'engine' | 'wheelDrive' | 'bodyType';
  onSetIndex: () => void;
}
type TrimCardProps = Common & (TrimCardPropsWithEngineInfo | TrimCardPropsWithoutEngineInfo);
export function TrimCard({
  isActive,
  title,
  price,
  description,
  hasEngineInfo,
  power,
  torque,
  id,
  dispatchKey,
  onSetIndex,
}: TrimCardProps) {
  const {
    dispatch,
    myCar: { options },
  } = useOutletContext<MyCarLayoutContextProps>();
  const [isOpen, setIsOpen] = useState(false);

  function handleCardClick() {
    if (isActive) {
      return;
    }

    if (options.length !== 0) {
      setIsOpen(true);
      return;
    }

    onSetIndex();
    dispatch({
      type: MyCarActionType.TRIM_OPTION,
      payload: { key: dispatchKey, name: title, additionalPrice: price, id },
    });
  }

  return (
    <>
      <Styled.Container isActive={isActive} onClick={handleCardClick}>
        <Styled.Wrapper>
          <Styled.Title>{title}</Styled.Title>
          <Styled.Price>+{price.toLocaleString()}원</Styled.Price>
        </Styled.Wrapper>
        <Styled.Description>{description}</Styled.Description>
        {hasEngineInfo && (
          <>
            <Styled.Line isActive={isActive} />
            <Styled.Info>
              <Styled.InfoTitle>최고출력</Styled.InfoTitle>
              <Styled.InfoContent>{power}</Styled.InfoContent>
            </Styled.Info>
            <Styled.Info>
              <Styled.InfoTitle>최대토크</Styled.InfoTitle>
              <Styled.InfoContent>{torque}</Styled.InfoContent>
            </Styled.Info>
          </>
        )}
      </Styled.Container>
      <ModalPortal isOpen={isOpen} handleClose={() => setIsOpen(false)}>
        <PopupModal
          type={ModalType.CLEAR}
          onClick={() => {
            dispatch({ type: MyCarActionType.CLEAR_OPTION, payload: [] });
            setIsOpen(false);
            onSetIndex();
          }}
          onClose={() => setIsOpen(false)}
        />
      </ModalPortal>
    </>
  );
}
