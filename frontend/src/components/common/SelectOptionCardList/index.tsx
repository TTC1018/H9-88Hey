import { useState, useEffect } from 'react';

import { SelectOptionCardDataProps } from '@/types/option';
import { isIndexLargeThanZero, isIndexSmallThanMaxIndex } from '@/utils';
import { OPTION_CARD_LIST_LENGTH } from '@/constants';

import { PrevButton } from '../PrevButton';
import { NextButton } from '../NextButton';

import * as style from './style';

interface SelectOptionCardListProps {
  isShow: boolean;
  selectedIndex: number;
  cardListIndex: number;
  data: SelectOptionCardDataProps[];
  onClickCard: (index: number, event: React.MouseEvent<HTMLDivElement>) => void;
  onClickArrowButton: (type: string, index: number, length: number) => void;
}

interface SelectOptionCardProps {
  index: number;
  name: string;
  price: number;
  imageUrl: string;
  subOptionNames: string[];
  isCardActive: boolean;
  onClickCard: (index: number, event: React.MouseEvent<HTMLDivElement>) => void;
}

interface SelectOptionCardHoverProps {
  subOptionNames: string[];
}

export function SelectOptionCardList({
  isShow,
  selectedIndex,
  cardListIndex,
  data,
  onClickCard,
  onClickArrowButton,
}: SelectOptionCardListProps) {
  const [cardList, setCardList] = useState<SelectOptionCardDataProps[]>([]);

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
      <PrevButton
        width="48"
        height="48"
        onClick={() => onClickArrowButton('SELECT', cardListIndex - 1, data.length)}
        isShow={isIndexLargeThanZero(cardListIndex)}
      />
      {cardList.map(({ index, name, price, imageUrl, subOptionNames }) => (
        <SelectOptionCard
          index={index}
          name={name}
          price={price}
          imageUrl={imageUrl}
          subOptionNames={subOptionNames}
          isCardActive={index === selectedIndex}
          onClickCard={onClickCard}
          key={index}
        />
      ))}
      <NextButton
        width="48"
        height="48"
        onClick={() => onClickArrowButton('SELECT', cardListIndex + 1, data.length)}
        isShow={isIndexSmallThanMaxIndex(cardListIndex, data.length)}
      />
    </style.Container>
  );
}

function SelectOptionCard({
  index,
  name,
  price,
  imageUrl,
  subOptionNames,
  isCardActive,
  onClickCard,
}: SelectOptionCardProps) {
  const [isButtonActive, setIsButtonActive] = useState(false);
  const [isHover, setIsHover] = useState(false);

  function handleClickButton() {
    setIsButtonActive(!isButtonActive);
  }

  function handleHoverButton(isHover: boolean) {
    setIsHover(isHover);
  }

  return (
    <style.OptionCard
      isCardActive={isCardActive}
      onClick={event => onClickCard(index, event)}
      onMouseEnter={() => handleHoverButton(true)}
      onMouseLeave={() => handleHoverButton(false)}
    >
      <style.Image src={imageUrl} />
      <style.DescriptionWrapper>
        <style.Text isCardActive={isCardActive}>{name}</style.Text>
        <style.Text isCardActive={isCardActive}>+{price.toLocaleString('en')}원</style.Text>
        <style.ButtonBox>
          <style.Button isButtonActive={isButtonActive} onClick={handleClickButton}>
            {isButtonActive ? '추가 완료' : '추가하기'}
          </style.Button>
        </style.ButtonBox>
        {isButtonActive && <style.Icon src={'/src/assets/icon_done.svg'} />}
      </style.DescriptionWrapper>
      {isHover && <SelectOptionCardHover subOptionNames={subOptionNames} />}
    </style.OptionCard>
  );
}

function SelectOptionCardHover({ subOptionNames }: SelectOptionCardHoverProps) {
  return (
    <style.OptionCardHover>
      <style.DescriptionHoverWrapper>
        {subOptionNames.map((name, index) => (
          <style.DescriptionHover key={index}>· {name}</style.DescriptionHover>
        ))}
      </style.DescriptionHoverWrapper>
    </style.OptionCardHover>
  );
}
