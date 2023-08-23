import { useState } from 'react';

import { useOutletContext } from 'react-router-dom';

import { ModalType, MyCarActionType } from '@/constants';
import { FeatureProps, MyCarLayoutContextProps } from '@/types/trim';

import { ModalPortal } from '@/components/Option/ModalPortal';
import { PopupModal } from '@/components/common/PopupModal';

import * as Styled from './style';

interface SelectOptionCardProps {
  isActive: boolean;
  id: number;
  name: string;
  price: number;
  features: FeatureProps[];
  onSetIndex: () => void;
}
export function SelectOptionCard({ isActive, id, name, price, features, onSetIndex }: SelectOptionCardProps) {
  const {
    dispatch,
    myCar: { exteriorColor, interiorColor, options },
  } = useOutletContext<MyCarLayoutContextProps>();
  const [isOpen, setIsOpen] = useState(false);

  function handleCardClick() {
    if (isActive) {
      return;
    }

    if (exteriorColor.name !== '' || interiorColor.name !== '' || options.length !== 0) {
      setIsOpen(true);
      return;
    }

    onSetIndex();
    dispatch({ type: MyCarActionType.TRIM, payload: { name, price, id } });
  }

  return (
    <>
      <Styled.Container isActive={isActive} onClick={handleCardClick}>
        <Styled.Title>{name}</Styled.Title>
        <Styled.Line isActive={isActive} />
        <Styled.ImageWrapper>
          {features.map(({ name, imageUrl }) => {
            const isBig = name.length >= 15;

            return (
              <Styled.IconWrapper key={name}>
                <Styled.Image src={imageUrl} />
                <Styled.Text isBig={isBig}>{name}</Styled.Text>
              </Styled.IconWrapper>
            );
          })}
        </Styled.ImageWrapper>
        <Styled.Line isActive={isActive} />
        <Styled.Price>{price.toLocaleString()} 원</Styled.Price>
      </Styled.Container>
      <ModalPortal isOpen={isOpen} handleClose={() => setIsOpen(false)}>
        <PopupModal
          type={ModalType.CLEAR}
          onClick={() => {
            dispatch({ type: MyCarActionType.CLEAR_COLORS, payload: null });
            dispatch({ type: MyCarActionType.CLEAR_OPTION, payload: [] });
            onSetIndex();
            setIsOpen(false);
          }}
          onClose={() => setIsOpen(false)}
        />
      </ModalPortal>
    </>
  );
}
