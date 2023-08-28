import { useState, useEffect, MouseEvent } from 'react';

import { useLocation, useOutletContext } from 'react-router-dom';

import { OptionCardDataProps } from '@/types/option';
import { OPTION_CARD_LIST_LENGTH } from '@/constants';
import { MyCarLayoutContextProps } from '@/types/trim';
import {
  isIndexLargeThanZero,
  isIndexSmallThanMaxIndex,
  checkIsHGenuineAccessoriesPage,
  checkIsNPerformancePage,
  isValidIndex,
} from '@/utils';

import { OptionCard } from './OptionCard';
import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';

import * as Styled from './style';

interface Props {
  selectedIndex: number;
  data: OptionCardDataProps[];
  onClickCard: (index: number, event: MouseEvent<HTMLDivElement>) => void;
}

export function OptionCardList({ selectedIndex, data, onClickCard }: Props) {
  const [cardListIndex, setCardListIndex] = useState(0);
  const [cardList, setCardList] = useState<OptionCardDataProps[]>([]);

  const { pathname } = useLocation();

  const { myCar } = useOutletContext<MyCarLayoutContextProps>();

  function handleChangeCardListIndex(index: number, length: number) {
    if (!isValidIndex(index, Math.ceil(length / OPTION_CARD_LIST_LENGTH) - 1)) {
      return;
    }
    setCardListIndex(index);
  }

  function checkIsNPerformanceSelected() {
    return myCar.options.filter(option => option.path === '/option/n-performance').length === 1;
  }

  function getNPerformanceId() {
    return myCar.options.find(option => option.path === '/option/n-performance')?.id;
  }

  function checkIsWheelSelected() {
    return checkIsNPerformancePage(pathname) && checkIsNPerformanceSelected();
  }

  function checkIsBlur(isAvailable: boolean | undefined, id: string) {
    if (pathname === '/option') {
      return false;
    }

    if (checkIsHGenuineAccessoriesPage(pathname)) {
      return !isAvailable;
    } else {
      return checkIsWheelSelected() && id !== getNPerformanceId();
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
        onClick={() => handleChangeCardListIndex(cardListIndex - 1, data.length)}
        isShow={isIndexLargeThanZero(cardListIndex)}
      />
      {cardList.map(({ isAvailable, index, id, ...props }) => (
        <OptionCard
          isBlur={checkIsBlur(isAvailable, id)}
          index={index}
          isCardActive={index === selectedIndex}
          onClickCard={onClickCard}
          key={index}
          id={id}
          {...props}
        />
      ))}
      <NextButton
        width="48"
        height="48"
        onClick={() => handleChangeCardListIndex(cardListIndex + 1, data.length)}
        isShow={isIndexSmallThanMaxIndex(cardListIndex, data.length)}
      />
    </Styled.Container>
  );
}
