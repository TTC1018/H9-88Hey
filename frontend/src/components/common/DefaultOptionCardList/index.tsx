import { useState, useEffect } from 'react';

import { DefaultOptionCardDataProps } from '@/types/option';
import { OPTION_CARD_LIST_LENGTH } from '@/constants';

import { PrevButton } from '../PrevButton';
import { NextButton } from '../NextButton';

import * as style from './style';

interface DefaultOptionCardListProps {
  isShow: boolean;
  categoryIndex: number;
  cardListIndex: number;
  categories: string[];
  data: DefaultOptionCardDataProps[];
  onClickCategory: (index: number) => void;
  onClickArrowButton: (type: string, index: number, length: number) => void;
}

export function DefaultOptionCardList({
  isShow,
  categoryIndex,
  cardListIndex,
  categories,
  data,
  onClickCategory,
  onClickArrowButton,
}: DefaultOptionCardListProps) {
  const [cardList, setCardList] = useState<DefaultOptionCardDataProps[]>([]);

  useEffect(() => {
    const startIndex = cardListIndex * OPTION_CARD_LIST_LENGTH;
    let endIndex = startIndex + OPTION_CARD_LIST_LENGTH;
    if (endIndex > data.length) {
      endIndex = data.length;
    }
    setCardList(data.slice(startIndex, endIndex));
  }, [cardListIndex, data]);

  return (
    <style.Container isShow={isShow}>
      <style.CategoryWrapper>
        {categories.map((category, index) => (
          <style.Category isActive={index === categoryIndex} onClick={() => onClickCategory(index)} key={category}>
            {category}
          </style.Category>
        ))}
      </style.CategoryWrapper>
      <style.OptionCardWrapper>
        <PrevButton
          width="48"
          height="48"
          onClick={() => onClickArrowButton('DEFAULT', cardListIndex - 1, data.length)}
          isShow={cardListIndex > 0}
        />
        {cardList.map(({ name, imageUrl }, index) => (
          <style.OptionCard key={index}>
            <style.Image src={imageUrl} />
            <style.TextWrapper>
              <style.Text>{name}</style.Text>
              <style.SubText>기본 포함</style.SubText>
            </style.TextWrapper>
          </style.OptionCard>
        ))}
        <NextButton
          width="48"
          height="48"
          onClick={() => onClickArrowButton('DEFAULT', cardListIndex + 1, data.length)}
          isShow={cardListIndex < Math.floor(data.length / OPTION_CARD_LIST_LENGTH)}
        />
      </style.OptionCardWrapper>
    </style.Container>
  );
}
