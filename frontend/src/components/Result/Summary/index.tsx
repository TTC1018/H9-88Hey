import { MyCarProps } from '@/types/trim';
import { hasJongSeong } from '@/utils';

import * as Styled from './style';

interface Props {
  trim: MyCarProps;
}

export function Summary({ trim }: Props) {
  const { carType, model, engine, wheelDrive, bodyType, exteriorColor, interiorColor } = trim;
  const { krName } = carType;
  const price =
    model.price +
    engine.additionalPrice +
    wheelDrive.additionalPrice +
    bodyType.additionalPrice +
    exteriorColor.additionalPrice;

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
              {engine.name} / {wheelDrive.name} / {bodyType.name}
            </Styled.Trim>
            <Styled.Price>{price.toLocaleString()}원</Styled.Price>
          </Styled.DetailWrapper>
          <Styled.SummaryLine />
          <Styled.ColorWrapper>
            <Styled.ColorType>외장</Styled.ColorType>
            <Styled.Ellipse src={exteriorColor.colorImageUrl} />
            <Styled.ColorName>{exteriorColor.name}</Styled.ColorName>
            <Styled.Space />
            <Styled.ColorType>내장</Styled.ColorType>
            <Styled.Ellipse src={interiorColor.colorImageUrl} />
            <Styled.ColorName>{interiorColor.name}</Styled.ColorName>
          </Styled.ColorWrapper>
        </Styled.SummaryWrapper>
      </Styled.Flex>
    </>
  );
}
