import { TrimOptionType } from '@types/trim';
import { SelectOptionCard } from '@components/trim/SelectOptionCard';
import * as style from './style';

interface TrimTemplateProps {
  selectedIndex: number;
  optionCards: TrimOptionType[];
  onClick: (index: number) => () => void;
}
export function TrimTemplate({ selectedIndex, optionCards, onClick }: TrimTemplateProps) {
  return (
    <style.Container>
      <style.Wrapper>
        {optionCards.map(({ title, price, images }, index) => (
          <style.Box key={title} onClick={onClick(index)}>
            <SelectOptionCard isActive={index === selectedIndex} title={title} price={price} images={images} />
          </style.Box>
        ))}
      </style.Wrapper>
    </style.Container>
  );
}
