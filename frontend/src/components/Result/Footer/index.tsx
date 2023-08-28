import * as Styled from './style';

interface Props {
  totalPrice: number;
}

export function Footer({ totalPrice }: Props) {
  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.FooterBox>
          <Styled.PriceText>예상 견적 가격</Styled.PriceText>
          <Styled.Price>{totalPrice.toLocaleString()}</Styled.Price>
          <Styled.PriceUnitBox>
            <Styled.PriceUnit>원</Styled.PriceUnit>
          </Styled.PriceUnitBox>
          <Styled.Button>이 차량 구매하기</Styled.Button>
        </Styled.FooterBox>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
