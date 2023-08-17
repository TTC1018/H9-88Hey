import { useState, useEffect, useRef, MouseEvent, WheelEvent } from 'react';

import { useOutletContext, useLocation } from 'react-router-dom';

import { OptionContextProviderProps } from '@/types/option';

import { OptionCardHover } from '@/components/Option/OptionCardList/OptionCardHover';

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
  onChangeCount: (step: number, index: number) => void;
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
  onChangeCount,
}: Props) {
  const [isButtonActive, setIsButtonActive] = useState(false);
  const [isHover, setIsHover] = useState(false);

  const { myCar, addOption, removeOption } = useOutletContext<OptionContextProviderProps>();

  const childRef = useRef<HTMLUListElement | null>(null);

  const { pathname } = useLocation();

  function handleClickButton(isBlur: boolean) {
    if (isBlur) {
      return;
    }

    setIsButtonActive(prev => !prev);

    if (isButtonActive) {
      removeOption(name);
      onChangeCount(-1, index);
    } else {
      addOption({ id, name, price: additionalPrice, imageUrl, subOptions: subOptionNames, path: pathname });
      onChangeCount(1, index);
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
    </Styled.OptionCardWrapper>
  );
}
