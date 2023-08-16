import { MyCarProps } from '@/types/trim';

import * as Styled from './style';

interface Props {
  onClick: () => void;
  myCarData: MyCarProps;
  totalPrice: number;
}
export function EstimateModal({ onClick, myCarData, totalPrice }: Props) {
  const { trim, engine, bodyType, wheelDrive, options, innerColor, outerColor } = myCarData;

  const trimOptions = `${engine.title}${bodyType.title !== '' ? '/' : ''}${bodyType.title}${
    wheelDrive.title !== '' ? '/' : ''
  }${wheelDrive.title}`;
  const trimPrice = engine.price + bodyType.price + wheelDrive.price;

  return (
    <>
      <Styled.Container onClick={onClick} />
      <Styled.ModalContainer>
        <Styled.Header>견적요약보기</Styled.Header>

        <Styled.TitleWrapper>
          <Styled.Title>총 견적 금액</Styled.Title>
          <Styled.Price>{totalPrice.toLocaleString()} 원</Styled.Price>
        </Styled.TitleWrapper>

        <Styled.DescriptionWrapper>
          <Styled.DescriptionBox>
            <Styled.Description>{trim.title}</Styled.Description>
            <Styled.Description>{trim.price.toLocaleString()} 원</Styled.Description>
          </Styled.DescriptionBox>
          <Styled.DescriptionBox>
            <Styled.Description>{trimOptions}</Styled.Description>
            <Styled.Description>+{trimPrice.toLocaleString()} 원</Styled.Description>
          </Styled.DescriptionBox>
        </Styled.DescriptionWrapper>

        <Styled.TitleWrapper>
          <Styled.Title>색상</Styled.Title>
        </Styled.TitleWrapper>

        <Styled.DescriptionWrapper>
          <Styled.DescriptionBox>
            <Styled.Title>외장</Styled.Title>
            <Styled.Description>{outerColor.title}</Styled.Description>
          </Styled.DescriptionBox>
          <Styled.DescriptionBox>
            <Styled.Title>내장</Styled.Title>
            <Styled.Description>{innerColor.title}</Styled.Description>
          </Styled.DescriptionBox>
        </Styled.DescriptionWrapper>

        <Styled.TitleWrapper>
          <Styled.Title>선택 옵션</Styled.Title>
        </Styled.TitleWrapper>

        <Styled.DescriptionWrapper>
          {options.map(option => (
            <Styled.DescriptionBox key={option.name}>
              <Styled.Description>{option.name}</Styled.Description>
              <Styled.Description>+{option.price.toLocaleString()}원</Styled.Description>
            </Styled.DescriptionBox>
          ))}
        </Styled.DescriptionWrapper>
      </Styled.ModalContainer>
    </>
  );
}
