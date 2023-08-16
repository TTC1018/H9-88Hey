import { useState, useEffect, MouseEvent } from 'react';

import { useOutletContext } from 'react-router-dom';

import { OptionCardDataProps, OptionContextProviderProps } from '@/types/option';
import { isIndexLargeThanZero, isIndexSmallThanMaxIndex } from '@/utils';
import { OPTION_CARD_LIST_LENGTH } from '@/constants';

import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';

import * as Styled from './style';

interface OptionCardListProps {
  selectedIndex: number;
  cardListIndex: number;
  data: OptionCardDataProps[];
  onClickCard: (index: number, event: MouseEvent<HTMLDivElement>) => void;
  onClickArrowButton: (index: number, length: number) => void;
}

interface OptionCardProps {
  index: number;
  name: string;
  additionalPrice: number;
  imageUrl: string;
  subOptionNames: string[];
  isCardActive: boolean;
  onClickCard: (index: number, event: MouseEvent<HTMLDivElement>) => void;
}

interface OptionCardHoverProps {
  subOptionNames: string[];
}

export function OptionCardList({
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

  return (
    <Styled.Container>
      <PrevButton
        width="48"
        height="48"
        onClick={() => onClickArrowButton(cardListIndex - 1, data.length)}
        isShow={isIndexLargeThanZero(cardListIndex)}
      />
      {cardList.map(({ index, name, additionalPrice, imageUrl, subOptionNames }) => (
        <OptionCard
          index={index}
          name={name}
          additionalPrice={additionalPrice}
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
    </Styled.Container>
  );
}

function OptionCard({
  index,
  name,
  additionalPrice,
  imageUrl,
  subOptionNames,
  isCardActive,
  onClickCard,
}: OptionCardProps) {
  const [isButtonActive, setIsButtonActive] = useState(false);
  const [isHover, setIsHover] = useState(false);

  const { myCar, addOption, removeOption } = useOutletContext<OptionContextProviderProps>();

  function handleClickButton() {
    setIsButtonActive(prev => !prev);
    isButtonActive
      ? removeOption(name)
      : addOption({ name, price: additionalPrice, imageUrl, subOptions: subOptionNames });
  }

  function handleHoverCard(isHover: boolean) {
    setIsHover(isHover);
  }

  useEffect(() => {
    const isOptionIncluded = myCar.options.some(option => option.name === name);
    setIsButtonActive(isOptionIncluded);
  }, [name]);

  return (
    <Styled.OptionCard isCardActive={isCardActive} onClick={event => onClickCard(index, event)}>
      <Styled.Image src={imageUrl} />
      <Styled.DescriptionWrapper>
        <Styled.Text isCardActive={isCardActive}>{name}</Styled.Text>
        <Styled.Text isCardActive={isCardActive}>+{additionalPrice.toLocaleString()}원</Styled.Text>
        <Styled.ButtonBox>
          <Styled.Button isButtonActive={isButtonActive} onClick={handleClickButton}>
            {isButtonActive ? '추가 완료' : '추가하기'}
          </Styled.Button>
        </Styled.ButtonBox>
        {isButtonActive && <Styled.Icon src={'/src/assets/icons/icon_done.svg'} />}
      </Styled.DescriptionWrapper>
      {isHover && <OptionCardHover subOptionNames={subOptionNames} />}
      <Styled.OptionCardHoverArea
        onMouseEnter={() => handleHoverCard(true)}
        onMouseLeave={() => handleHoverCard(false)}
      />
    </Styled.OptionCard>
  );
}

function OptionCardHover({ subOptionNames }: OptionCardHoverProps) {
  return (
    <Styled.OptionCardHover>
      <Styled.DescriptionHoverWrapper>
        {subOptionNames.map((name, index) => (
          <Styled.DescriptionHover key={index}>· {name}</Styled.DescriptionHover>
        ))}
      </Styled.DescriptionHoverWrapper>
    </Styled.OptionCardHover>
  );
}
