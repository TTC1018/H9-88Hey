import * as Styled from './style';

export function ReviewTextArea() {
  return (
    <Styled.Container>
      <Styled.TextWrapper>
        <Styled.TextBox>
          <Styled.Title>리뷰를 남겨주세요</Styled.Title>
          <Styled.SubTitle>(선택사항)</Styled.SubTitle>
        </Styled.TextBox>
        <Styled.SubTitle>(0/140)</Styled.SubTitle>
      </Styled.TextWrapper>
      <Styled.TextArea placeholder="직접 옵션에 대해 이야기해주세요!" />
    </Styled.Container>
  );
}
