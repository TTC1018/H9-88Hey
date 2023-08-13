import { FeatureType } from '@/types/trim';

import * as Styled from './style';

interface SelectOptionCardProps {
  isActive: boolean;
  name: string;
  price: number;
  features: FeatureType[];
}
export function SelectOptionCard({ isActive, name, price, features }: SelectOptionCardProps) {
  return (
    <Styled.Container isActive={isActive}>
      <Styled.Title>{name}</Styled.Title>
      <Styled.Line isActive={isActive} />
      <Styled.ImageWrapper>
        {features.map(({ name, icon }) => {
          const isBig = name.length >= 15;

          return (
            <Styled.IconWrapper key={name}>
              <Styled.Image src={icon} />
              <Styled.Text isBig={isBig}>{name}</Styled.Text>
            </Styled.IconWrapper>
          );
        })}
      </Styled.ImageWrapper>
      <Styled.Line isActive={isActive} />
      <Styled.Price>{price.toLocaleString()} 원</Styled.Price>
    </Styled.Container>
  );
}
