import { TrimOptionType } from '@/types/trim';
import { MyCarImageBox } from '@/components/common/MyCarImageBox';
import { SelectOptionCard } from '@/components/trim/SelectOptionCard';
import * as style from './style';

interface TrimTemplateProps {
  selectedIndex: number;
  selectedImageIndex: number;
  optionCards: TrimOptionType[];
  onCardClick: (index: number) => () => void;
  onImageClick: (index: number) => () => void;
}
export function TrimTemplate({
  selectedIndex,
  selectedImageIndex,
  optionCards,
  onCardClick,
  onImageClick,
}: TrimTemplateProps) {
  const { carImages } = optionCards[selectedIndex];

  return (
    <style.Container>
      <MyCarImageBox hasOption={true} images={carImages} selectedIndex={selectedImageIndex} onClick={onImageClick} />
      <style.Wrapper>
        {optionCards.map(({ title, price, optionImages }, index) => (
          <style.Box key={title} onClick={onCardClick(index)}>
            <SelectOptionCard isActive={index === selectedIndex} title={title} price={price} images={optionImages} />
          </style.Box>
        ))}
      </style.Wrapper>
    </style.Container>
  );
}
