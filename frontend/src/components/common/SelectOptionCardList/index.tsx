import { useState } from 'react';

import { SelectOptionCardDataProps } from '@/types/option';
import { addCommasToPrice } from '@/utils';

import * as style from './style';

interface SelectOptionCardListProps {
  selectedIndex: number;
  data: SelectOptionCardDataProps[];
  onClickCard: (index: number, event: React.MouseEvent<HTMLDivElement>) => void;
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

export function SelectOptionCardList({ selectedIndex, data, onClickCard }: SelectOptionCardListProps) {
  return (
    <style.Container>
      {data.map(({ name, price, imageUrl, subOptionNames }, index) => (
        <SelectOptionCard
          index={index}
          name={name}
          price={price}
          imageUrl={imageUrl}
          subOptionNames={subOptionNames}
          isCardActive={index === selectedIndex}
          onClickCard={onClickCard}
          key={name}
        />
      ))}
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
        <style.Text isCardActive={isCardActive}>{addCommasToPrice(price)}</style.Text>
        <style.ButtonBox>
          <style.Button isButtonActive={isButtonActive} onClick={handleClickButton}>
            {isButtonActive ? '추가 완료' : '추가하기'}
          </style.Button>
        </style.ButtonBox>
        {isButtonActive && <style.Icon src={'src/assets/icon_done.svg'} />}
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
          <style.DescriptionHover key={index}>{name}</style.DescriptionHover>
        ))}
      </style.DescriptionHoverWrapper>
    </style.OptionCardHover>
  );
}
