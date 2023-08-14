import * as Styled from './style';

const carPrice = 47340000;

export function Summary() {
  return (
    <>
      <Styled.Flex>
        <Styled.SubTitle>나의 펠리세이드는 이런 기능을 가지고 있어요</Styled.SubTitle>
      </Styled.Flex>
      <Styled.Flex>
        <Styled.SummaryWrapper>
          <Styled.Name>펠리세이드 Le Blanc(르블랑)</Styled.Name>
          <Styled.DetailWrapper>
            <Styled.Trim>디젤 2.2 / 4WD / 7인승</Styled.Trim>
            <Styled.Price>{carPrice.toLocaleString()}원</Styled.Price>
          </Styled.DetailWrapper>
          <Styled.SummaryLine />
          <Styled.ColorWrapper>
            <Styled.ColorType>외장</Styled.ColorType>
            <Styled.Ellipse src="/src/assets/icons/ellipse_567.svg" />
            <Styled.ColorName>문라이트 블루펄</Styled.ColorName>
            <Styled.Space />
            <Styled.ColorType>내장</Styled.ColorType>
            <Styled.Ellipse src="/src/assets/icons/ellipse_123.png" />
            <Styled.ColorName>퀄팅 천연(블랙)</Styled.ColorName>
          </Styled.ColorWrapper>
        </Styled.SummaryWrapper>
      </Styled.Flex>
    </>
  );
}
