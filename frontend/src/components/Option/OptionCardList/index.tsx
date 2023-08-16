import { useState, useEffect, MouseEvent } from 'react';

import { useLocation } from 'react-router-dom';

import { OptionCardDataProps } from '@/types/option';
import { isIndexLargeThanZero, isIndexSmallThanMaxIndex } from '@/utils';
import { OPTION_CARD_LIST_LENGTH } from '@/constants';

import { OptionCard } from './OptionCard';
import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';

import * as Styled from './style';

interface Props {
  selectedIndex: number;
  cardListIndex: number;
  data: OptionCardDataProps[];
  onClickCard: (index: number, event: MouseEvent<HTMLDivElement>) => void;
  onClickArrowButton: (index: number, length: number) => void;
}

export function OptionCardList({ selectedIndex, cardListIndex, data, onClickCard, onClickArrowButton }: Props) {
  const [cardList, setCardList] = useState<OptionCardDataProps[]>([]);
  const [selectedCount, setSelectedCount] = useState(0);
  const [lastAddedCardIndex, setLastAddedCardIndex] = useState<number | null>(null);

  const { pathname } = useLocation();

  function handleSelectedCount(step: number, index: number) {
    setSelectedCount(prev => prev + step);
    setLastAddedCardIndex(index);
  }

  function checkIsWheelSelected() {
    return pathname === '/option/n-performance' && selectedCount === 1;
  }

  function checkIsBlur(isAvailable: boolean | undefined, index: number) {
    if (pathname === '/option') {
      return false;
    }

    if (pathname === '/option/h-genuine-accessories') {
      return !isAvailable;
    } else {
      return checkIsWheelSelected() && index !== lastAddedCardIndex;
    }
  }

  useEffect(() => {
    const startIndex = cardListIndex * OPTION_CARD_LIST_LENGTH;
    let endIndex = startIndex + OPTION_CARD_LIST_LENGTH;
    if (endIndex > data.length) {
      endIndex = data.length;
    }
    setCardList(data.slice(startIndex, endIndex));
  }, [cardListIndex, data]);

  return (
    <Styled.Container>
      <PrevButton
        width="48"
        height="48"
        onClick={() => onClickArrowButton(cardListIndex - 1, data.length)}
        isShow={isIndexLargeThanZero(cardListIndex)}
      />
      {cardList.map(({ isAvailable, index, ...props }) => (
        <OptionCard
          isBlur={checkIsBlur(isAvailable, index)}
          index={index}
          isCardActive={index === selectedIndex}
          onClickCard={onClickCard}
          onChangeCount={handleSelectedCount}
          key={index}
          {...props}
        />
      ))}
      <NextButton
        width="48"
        height="48"
        onClick={() => onClickArrowButton(cardListIndex + 1, data.length)}
        isShow={isIndexSmallThanMaxIndex(cardListIndex, data.length)}
      />
    </Styled.Container>
  );
}
