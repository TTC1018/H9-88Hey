import { SelectOptionCardInfoProps } from '@/types/option';

import * as style from './style';

interface SelectOptionCardProps {
  selectedOption: number;
  cardInfo: SelectOptionCardInfoProps[];
}

interface OptionCardProps {
  option: number;
  selectedOption: number;
  image: string;
  title: string;
  price: string;
  descriptions: string[];
}

interface OptionCardHoverProps {
  descriptions: string[];
}

export function SelectOptionCard({ selectedOption, cardInfo }: SelectOptionCardProps) {
  return (
    <style.Container>
      {cardInfo.map(({ image, title, price, descriptions }, index) => (
        <OptionCard
          option={index + 1}
          selectedOption={selectedOption}
          image={image}
          title={title}
          price={price.toString()}
          descriptions={descriptions}
          key={title}
        />
      ))}
    </style.Container>
  );
}

function OptionCard({ option, selectedOption, image, title, price, descriptions }: OptionCardProps) {
  return (
    <style.OptionCard option={option} selectedOption={selectedOption}>
      <style.Image src={image} />
      <style.DescriptionWrapper>
        <style.Text option={option} selectedOption={selectedOption}>
          {title}
        </style.Text>
        <style.Text option={option} selectedOption={selectedOption}>
          {price}
        </style.Text>
        <style.ButtonBox>
          <style.Button option={option} selectedOption={selectedOption}>
            {option === selectedOption ? '추가 완료' : '추가하기'}
          </style.Button>
        </style.ButtonBox>
        {option === selectedOption && <style.Icon src={'src/assets/icon_done.svg'} />}
      </style.DescriptionWrapper>
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
