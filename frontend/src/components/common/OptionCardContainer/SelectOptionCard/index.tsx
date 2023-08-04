import { SelectOptionCardInfoProps } from '@/types/option';
import { addCommasToPrice } from '@/utils';

import * as style from './style';

interface SelectOptionCardProps {
  selectOption: number;
  activeButtons: Set<number>;
  cardInfo: SelectOptionCardInfoProps[];
}

interface OptionCardProps {
  option: number;
  selectOption: number;
  image: string;
  title: string;
  price: number;
  descriptions: string[];
  isButtonActive: boolean;
}

interface OptionCardHoverProps {
  descriptions: string[];
}

export function SelectOptionCard({ selectOption, activeButtons, cardInfo }: SelectOptionCardProps) {
  return (
    <style.Container>
      {cardInfo.map(({ image, title, price, descriptions }, index) => (
        <OptionCard
          option={index + 1}
          selectOption={selectOption}
          image={image}
          title={title}
          price={price}
          descriptions={descriptions}
          isButtonActive={activeButtons.has(index + 1)}
          key={title}
        />
      ))}
    </style.Container>
  );
}

function OptionCard({ option, selectOption, image, title, price, descriptions, isButtonActive }: OptionCardProps) {
  return (
    <style.OptionCard isCardActive={option === selectOption}>
      <style.Image src={image} />
      <style.DescriptionWrapper>
        <style.Text isCardActive={option === selectOption}>{title}</style.Text>
        <style.Text isCardActive={option === selectOption}>{addCommasToPrice(price)}</style.Text>
        <style.ButtonBox>
          <style.Button isButtonActive={isButtonActive}>{isButtonActive ? '추가 완료' : '추가하기'}</style.Button>
        </style.ButtonBox>
        {isButtonActive && <style.Icon src={'src/assets/icon_done.svg'} />}
      </style.DescriptionWrapper>
      {/* TODO: hover 시에만 바뀌도록 수정 필요 */}
      {option === 2 && <OptionCardHover descriptions={descriptions} />}
    </style.OptionCard>
  );
}

function OptionCardHover({ descriptions }: OptionCardHoverProps) {
  return (
    <style.OptionCardHover>
      <style.DescriptionHoverWrapper>
        {descriptions.map(description => (
          <style.DescriptionHover key={description}>{description}</style.DescriptionHover>
        ))}
      </style.DescriptionHoverWrapper>
    </style.OptionCardHover>
  );
}
