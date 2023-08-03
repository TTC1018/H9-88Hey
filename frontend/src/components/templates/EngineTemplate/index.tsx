import { EngineOptionType } from '@/types/trim';
import { TrimCard } from '@/components/common/TrimCard';
import { MyCarImageBox } from '@/components/common/MyCarImageBox';
import { MyCarDescription } from '@/components/common/MyCarDescription';
import * as style from './style';

interface EngineTemplateProps {
  selectedIndex: number;
  optionCards: EngineOptionType[];
  onClick: (index: number) => () => void;
}
export function EngineTemplate({ selectedIndex, optionCards, onClick }: EngineTemplateProps) {
  const { image, price, title } = optionCards[selectedIndex];
  return (
    <style.Container>
      <style.Wrapper>
        <MyCarImageBox hasOption={false} images={image} />
        <MyCarDescription title={title} price={price} hasTag={false} />
      </style.Wrapper>
      <style.Wrapper>
        {optionCards.map(({ title, price, description, power, torque }, index) => (
          <style.Box key={title} onClick={onClick(index)}>
            <TrimCard
              isActive={index === selectedIndex}
              title={title}
              price={price}
              description={description}
              hasEngineInfo={true}
              power={power}
              torque={torque}
            />
          </style.Box>
        ))}
      </style.Wrapper>
    </style.Container>
  );
}
