import { useState, useEffect, useRef, MouseEvent, WheelEvent } from 'react';

import { useOutletContext, useLocation } from 'react-router-dom';

import { MyCarLayoutContextProps } from '@/types/trim';
import { ModalType, MyCarActionType } from '@/constants';
import { checkIsSelectOptionPage, isHGenuineAccessoriesSelected } from '@/utils';

import { OptionCardHover } from '@/components/Option/OptionCardList/OptionCardHover';
import { ModalPortal } from '@/components/Option/ModalPortal';
import { PopupModal } from '@/components/common/PopupModal';

import Done from '/public/assets/icons/icon_done.svg';

import * as Styled from './style';

interface Props {
  isBlur: boolean;
  index: number;
  id: string;
  name: string;
  additionalPrice: number;
  imageUrl: string;
  subOptionNames: string[];
  isCardActive: boolean;
  onClickCard: (index: number, event: MouseEvent<HTMLDivElement>) => void;
}

export function OptionCard({
  isBlur,
  index,
  id,
  name,
  additionalPrice,
  imageUrl,
  subOptionNames,
  isCardActive,
  onClickCard,
}: Props) {
  const [isButtonActive, setIsButtonActive] = useState(false);
  const [isHover, setIsHover] = useState(false);
  const [isOpen, setIsOpen] = useState(false);

  const { myCar, dispatch } = useOutletContext<MyCarLayoutContextProps>();
  const { options } = myCar;

  const childRef = useRef<HTMLUListElement | null>(null);

  const { pathname } = useLocation();

  function handleClickButton(isBlur: boolean) {
    if (isBlur) {
      return;
    }

    if (checkIsSelectOptionPage(pathname) && isHGenuineAccessoriesSelected(options)) {
      setIsOpen(true);
      return;
    }

    setIsButtonActive(prev => !prev);

    isButtonActive
      ? dispatch({ type: MyCarActionType.REMOVE_OPTION, payload: name })
      : dispatch({
          type: MyCarActionType.ADD_OPTION,
          payload: { id, name, additionalPrice, imageUrl, subOptions: subOptionNames, path: pathname },
        });
  }

  function handleHoverCard(isHover: boolean) {
    setIsHover(isHover);
  }

  function handleWheel(event: WheelEvent<HTMLDivElement>) {
    if (childRef.current !== null) {
      const childElement = childRef.current;
      childElement.scrollBy({ top: event.deltaY, behavior: 'auto' });
    }
  }

  function handleClearHGenuineAccessories() {
    const clearedOptions = myCar.options.filter(option => option.path !== '/option/h-genuine-accessories');
    dispatch({ type: MyCarActionType.CLEAR_OPTION, payload: clearedOptions });

    setIsButtonActive(prev => !prev);

    isButtonActive
      ? dispatch({ type: MyCarActionType.REMOVE_OPTION, payload: name })
      : dispatch({
          type: MyCarActionType.ADD_OPTION,
          payload: { id, name, additionalPrice, imageUrl, subOptions: subOptionNames, path: pathname },
        });

    setIsOpen(false);
  }

  useEffect(() => {
    const isOptionIncluded = myCar.options.some(option => option.name === name);
    setIsButtonActive(isOptionIncluded);
  }, [name]);

  return (
    <Styled.OptionCardWrapper isCardActive={isCardActive} onClick={event => onClickCard(index, event)}>
      <Styled.Image src={imageUrl} />
      <Styled.DescriptionWrapper>
        <Styled.Text isCardActive={isCardActive}>{name}</Styled.Text>
        <Styled.Text isCardActive={isCardActive}>+{additionalPrice.toLocaleString()}원</Styled.Text>
        <Styled.ButtonBox>
          <Styled.Button isBlur={isBlur} isButtonActive={isButtonActive} onClick={() => handleClickButton(isBlur)}>
            {isButtonActive ? '추가 완료' : '추가하기'}
          </Styled.Button>
        </Styled.ButtonBox>
        {isButtonActive && <Styled.Icon src={Done} />}
      </Styled.DescriptionWrapper>
      {isHover && <OptionCardHover subOptionNames={subOptionNames} childRef={childRef} />}
      <Styled.OptionCardHoverArea
        onMouseEnter={() => handleHoverCard(true)}
        onMouseLeave={() => handleHoverCard(false)}
        onWheel={event => handleWheel(event)}
      />
      <ModalPortal isOpen={isOpen} handleClose={() => setIsOpen(false)}>
        <PopupModal type={ModalType.CLEAR} onClick={handleClearHGenuineAccessories} onClose={() => setIsOpen(false)} />
      </ModalPortal>
    </Styled.OptionCardWrapper>
  );
}
