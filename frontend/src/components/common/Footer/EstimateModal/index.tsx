import { MyCarProps } from '@/types/trim';

import * as Styled from './style';

interface Props {
  onClick: () => void;
  myCarData: MyCarProps;
  totalPrice: number;
}
export function EstimateModal({ onClick, myCarData, totalPrice }: Props) {
  const {
    model,
    engine,
    bodyType,
    wheelDrive,
    // color, options
  } = myCarData;

  const trim = `${engine.title}${bodyType.title !== '' ? '/' : ''}${bodyType.title}${
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
            <Styled.Description>{model.title}</Styled.Description>
            <Styled.Description>{model.price.toLocaleString()} 원</Styled.Description>
          </Styled.DescriptionBox>
          <Styled.DescriptionBox>
            <Styled.Description>{trim}</Styled.Description>
            <Styled.Description>+{trimPrice.toLocaleString()} 원</Styled.Description>
          </Styled.DescriptionBox>
        </Styled.DescriptionWrapper>

        <Styled.TitleWrapper>
          <Styled.Title>색상</Styled.Title>
        </Styled.TitleWrapper>

        <Styled.DescriptionWrapper>
          <Styled.DescriptionBox>
            <Styled.Title>외장</Styled.Title>
          </Styled.DescriptionBox>
          <Styled.DescriptionBox>
            <Styled.Title>내장</Styled.Title>
          </Styled.DescriptionBox>
        </Styled.DescriptionWrapper>

        <Styled.TitleWrapper>
          <Styled.Title>선택 옵션</Styled.Title>
        </Styled.TitleWrapper>

        <Styled.DescriptionWrapper>
          <Styled.DescriptionBox>
            <Styled.Description>컴포트 ||</Styled.Description>
            <Styled.Description>+1,090,000 원</Styled.Description>
          </Styled.DescriptionBox>
          <Styled.DescriptionBox>
            <Styled.Description>컴포트 ||</Styled.Description>
            <Styled.Description>+1,090,000 원</Styled.Description>
          </Styled.DescriptionBox>
          <Styled.DescriptionBox>
            <Styled.Description>컴포트 ||</Styled.Description>
            <Styled.Description>+1,090,000 원</Styled.Description>
          </Styled.DescriptionBox>
          <Styled.DescriptionBox>
            <Styled.Description>컴포트 ||</Styled.Description>
            <Styled.Description>+1,090,000 원</Styled.Description>
          </Styled.DescriptionBox>
          <Styled.DescriptionBox>
            <Styled.Description>컴포트 ||</Styled.Description>
            <Styled.Description>+1,090,000 원</Styled.Description>
          </Styled.DescriptionBox>
        </Styled.DescriptionWrapper>
      </Styled.ModalContainer>
    </>
  );
}
