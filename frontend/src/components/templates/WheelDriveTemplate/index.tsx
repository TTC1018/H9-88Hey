import { WheelDriveOptionType } from '@types/trim';
import { TrimCard } from '@components/common/TrimCard';
import { MyCarImageBox } from '@components/common/MyCarImageBox';
import { MyCarDescription } from '@components/common/MyCarDescription';
import * as style from './style';

interface EngineTemplateProps {
  selectedIndex: number;
  optionCards: WheelDriveOptionType[];
  onClick: (index: number) => () => void;
}
export function WheelDriveTemplate({ selectedIndex, optionCards, onClick }: EngineTemplateProps) {
  const { title, price, image } = optionCards[selectedIndex];

  return (
    <style.Container>
      <style.ImageWrapper>
        <MyCarImageBox hasOption={false} images={image} />
        <MyCarDescription title={title} price={price} hasTag={false} />
      </style.ImageWrapper>
      <style.CardWrapper>
        {optionCards.map(({ title, price, description }, index) => (
          <style.Box key={title} onClick={onClick(index)}>
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
