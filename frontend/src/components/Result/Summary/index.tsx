import { MyCarProps } from '@/types/trim';
import { hasJongSeong } from '@/utils';

import * as Styled from './style';

interface Props {
  trim: MyCarProps;
}

export function Summary({ trim }: Props) {
  const { carType, model, engine, wheelDrive, bodyType, outerColor, innerColor } = trim;
  const { krName } = carType;
  const price = model.price + engine.price + wheelDrive.price + bodyType.price + outerColor.price;

  return (
    <>
      <Styled.Flex>
        <Styled.SubTitle>
          나의 {krName}
          {hasJongSeong(krName) ? '은' : '는'} 이런 기능을 가지고 있어요
        </Styled.SubTitle>
      </Styled.Flex>
      <Styled.Flex>
        <Styled.SummaryWrapper>
          <Styled.Name>
            {krName} {model.title}
          </Styled.Name>
          <Styled.DetailWrapper>
            <Styled.Trim>
              {engine.title} / {wheelDrive.title} / {bodyType.title}
            </Styled.Trim>
            <Styled.Price>{price.toLocaleString()}원</Styled.Price>
          </Styled.DetailWrapper>
          <Styled.SummaryLine />
          <Styled.ColorWrapper>
            <Styled.ColorType>외장</Styled.ColorType>
            <Styled.Ellipse src={outerColor.imageUrl} />
            <Styled.ColorName>{outerColor.title}</Styled.ColorName>
            <Styled.Space />
            <Styled.ColorType>내장</Styled.ColorType>
            <Styled.Ellipse src={innerColor.imageUrl} />
            <Styled.ColorName>{innerColor.title}</Styled.ColorName>
          </Styled.ColorWrapper>
        </Styled.SummaryWrapper>
      </Styled.Flex>
    </>
  );
}
