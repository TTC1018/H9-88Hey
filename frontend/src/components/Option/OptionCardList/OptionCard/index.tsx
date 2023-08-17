import { useState, useEffect, useRef, MouseEvent, WheelEvent } from 'react';

import { useOutletContext, useLocation } from 'react-router-dom';

import { OptionContextProviderProps } from '@/types/option';
import { checkIsSelectOptionPage, isHGenuineAccessoriesSelected } from '@/utils';
import { useModalContext } from '@/hooks/useModalContext';
import { ModalType } from '@/constants';

import { OptionCardHover } from '@/components/Option/OptionCardList/OptionCardHover';
import { ModalPortal } from '@/components/common/ModalPortal';
import { PopupModal } from '@/components/common/PopupModal';

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

  const { myCar, addOption, removeOption, clearHGenuineAccessories } = useOutletContext<OptionContextProviderProps>();
  const { options } = myCar;

  const childRef = useRef<HTMLUListElement | null>(null);

  const { pathname } = useLocation();

  const { handleOpen } = useModalContext();

  function handleClickButton(isBlur: boolean) {
    if (isBlur) {
      return;
    }

    if (checkIsSelectOptionPage(pathname) && isHGenuineAccessoriesSelected(options)) {
      handleOpen();
      return;
    }

    setIsButtonActive(prev => !prev);

    if (isButtonActive) {
      removeOption(name);
    } else {
      addOption({ id, name, price: additionalPrice, imageUrl, subOptions: subOptionNames, path: pathname });
    }
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
    clearHGenuineAccessories();

    setIsButtonActive(prev => !prev);

    if (isButtonActive) {
      removeOption(name);
    } else {
      addOption({ id, name, price: additionalPrice, imageUrl, subOptions: subOptionNames, path: pathname });
    }
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
        {isButtonActive && <Styled.Icon src={'/src/assets/icons/icon_done.svg'} />}
      </Styled.DescriptionWrapper>
      {isHover && <OptionCardHover subOptionNames={subOptionNames} ref={childRef} />}
      <Styled.OptionCardHoverArea
        onMouseEnter={() => handleHoverCard(true)}
        onMouseLeave={() => handleHoverCard(false)}
        onWheel={event => handleWheel(event)}
      />
      <ModalPortal>
        <PopupModal type={ModalType.CLEAR} onClick={handleClearHGenuineAccessories} />
      </ModalPortal>
    </Styled.OptionCardWrapper>
  );
}
