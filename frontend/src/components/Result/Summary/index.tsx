import { MyCarProps } from '@/types/trim';
import { combineWithSlash, hasJongSeong } from '@/utils';

import * as Styled from './style';

interface Props {
  myCar: MyCarProps;
}

export function Summary({ myCar }: Props) {
  const { carType, trim, engine, wheelDrive, bodyType, exteriorColor, interiorColor } = myCar;
  const { krName } = carType;

  const price =
    trim.price +
    engine.additionalPrice +
    wheelDrive.additionalPrice +
    bodyType.additionalPrice +
    exteriorColor.additionalPrice;

  const trimOptions = combineWithSlash([engine.name, bodyType.name, wheelDrive.name]);

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
          <Styled.Name>{krName}</Styled.Name>
          <Styled.DetailWrapper>
            <Styled.Trim>{trimOptions}</Styled.Trim>
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
