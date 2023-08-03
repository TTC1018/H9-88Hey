import { WheelTypeOptionType } from '@types/trim';
import { TrimCard } from '@components/common/TrimCard';
import { MyCarImageBox } from '@components/common/MyCarImageBox';
import { MyCarDescription } from '@components/common/MyCarDescription';
import * as style from './style';

interface EngineTemplateProps {
  selectedIndex: number;
  selectedImageIndex: number;
  selectOptionCards: WheelTypeOptionType[];
  onCardClick: (index: number) => () => void;
  onImageClick: (index: number) => () => void;
}
export function BodyTypeTemplate({
  selectedIndex,
  selectedImageIndex,
  selectOptionCards,
  onCardClick,
  onImageClick,
}: EngineTemplateProps) {
  const { title, price, images } = selectOptionCards[selectedIndex];

  return (
    <style.Container>
      <style.ImageWrapper>
        <MyCarImageBox hasOption={true} images={images} selectedIndex={selectedImageIndex} onClick={onImageClick} />
        <MyCarDescription title={title} price={price} hasTag={false} />
      </style.ImageWrapper>
      <style.CardWrapper>
        {selectOptionCards.map(({ title, price, description }, index) => (
          <style.Box key={title} onClick={onCardClick(index)}>
            <TrimCard
              isActive={index === selectedIndex}
              title={title}
              price={price}
              description={description}
              hasEngineInfo={false}
            />
          </style.Box>
        ))}
      </style.CardWrapper>
    </style.Container>
  );
}
