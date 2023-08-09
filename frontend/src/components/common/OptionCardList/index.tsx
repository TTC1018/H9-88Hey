import { useState, useEffect, MouseEvent } from 'react';

import { useOutletContext } from 'react-router-dom';

import { OptionCardDataProps, OptionContextProviderProps } from '@/types/option';
import { isIndexLargeThanZero, isIndexSmallThanMaxIndex } from '@/utils';
import { OPTION_CARD_LIST_LENGTH } from '@/constants';

import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';

import * as style from './style';

interface OptionCardListProps {
  isShow: boolean;
  selectedIndex: number;
  cardListIndex: number;
  data: OptionCardDataProps[];
  onClickCard: (index: number, event: MouseEvent<HTMLDivElement>) => void;
  onClickArrowButton: (index: number, length: number) => void;
}

interface OptionCardProps {
  index: number;
  name: string;
  price: number;
  imageUrl: string;
  subOptionNames: string[];
  isCardActive: boolean;
  onClickCard: (index: number, event: MouseEvent<HTMLDivElement>) => void;
}

interface OptionCardHoverProps {
  subOptionNames: string[];
}

export function OptionCardList({
  isShow,
  selectedIndex,
  cardListIndex,
  data,
  onClickCard,
  onClickArrowButton,
}: OptionCardListProps) {
  const [cardList, setCardList] = useState<OptionCardDataProps[]>([]);

  useEffect(() => {
    const startIndex = cardListIndex * OPTION_CARD_LIST_LENGTH;
    let endIndex = startIndex + OPTION_CARD_LIST_LENGTH;
    if (endIndex > data.length) {
      endIndex = data.length;
    }
    setCardList(data.slice(startIndex, endIndex));
  }, [cardListIndex, data]);

  useEffect(() => {}, []);

  return (
    <style.Container isShow={isShow}>
      <PrevButton
        width="48"
        height="48"
        onClick={() => onClickArrowButton(cardListIndex - 1, data.length)}
        isShow={isIndexLargeThanZero(cardListIndex)}
      />
      {cardList.map(({ index, name, price, imageUrl, subOptionNames }) => (
        <OptionCard
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
        onClick={() => onClickArrowButton(cardListIndex + 1, data.length)}
        isShow={isIndexSmallThanMaxIndex(cardListIndex, data.length)}
      />
    </style.Container>
  );
}

function OptionCard({ index, name, price, imageUrl, subOptionNames, isCardActive, onClickCard }: OptionCardProps) {
  const [isButtonActive, setIsButtonActive] = useState(false);
  const [isHover, setIsHover] = useState(false);

  const { addOption, removeOption } = useOutletContext<OptionContextProviderProps>();

  function handleClickButton() {
    setIsButtonActive(!isButtonActive);
    isButtonActive ? removeOption(name) : addOption({ name, price });
  }

  function handleHoverCard(isHover: boolean) {
    setIsHover(isHover);
  }

  return (
    <style.OptionCard isCardActive={isCardActive} onClick={event => onClickCard(index, event)}>
      <style.Image src={imageUrl} />
      <style.DescriptionWrapper>
        <style.Text isCardActive={isCardActive}>{name}</style.Text>
        <style.Text isCardActive={isCardActive}>+{price.toLocaleString()}원</style.Text>
        <style.ButtonBox>
          <style.Button isButtonActive={isButtonActive} onClick={handleClickButton}>
            {isButtonActive ? '추가 완료' : '추가하기'}
          </style.Button>
        </style.ButtonBox>
        {isButtonActive && <style.Icon src={'/src/assets/icons/icon_done.svg'} />}
      </style.DescriptionWrapper>
      {isHover && <OptionCardHover subOptionNames={subOptionNames} />}
      <style.OptionCardHoverArea
        onMouseEnter={() => handleHoverCard(true)}
        onMouseLeave={() => handleHoverCard(false)}
      />
    </style.OptionCard>
  );
}

function OptionCardHover({ subOptionNames }: OptionCardHoverProps) {
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
