import { FeatureProps } from '@/types/trim';

import * as style from './style';

interface SelectOptionCardProps {
  isActive: boolean;
  name: string;
  price: number;
  features: FeatureProps[];
}
export function SelectOptionCard({ isActive, name, price, features }: SelectOptionCardProps) {
  return (
    <style.Container isActive={isActive}>
      <style.Title>{name}</style.Title>
      <style.Line isActive={isActive} />
      <style.ImageWrapper>
        {features.map(({ name, icon }) => {
          const isBig = name.length >= 15;

          return (
            <style.IconWrapper key={name}>
              <style.Image src={icon} />
              <style.Text isBig={isBig}>{name}</style.Text>
            </style.IconWrapper>
          );
        })}
      </style.ImageWrapper>
      <style.Line isActive={isActive} />
      <style.Price>{price.toLocaleString()} 원</style.Price>
    </style.Container>
  );
}
