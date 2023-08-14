import { ReviewButton } from '../ReviewButton';
import * as Styled from './style';

export function ReviewComplete() {
  return (
    <Styled.Container>
      <Styled.Title>후기를 작성해주셔서 감사해요!</Styled.Title>
      <Styled.Wrapper>
        <Styled.Description>작성한 후기는 아래 링크에서 30일동안 볼 수 있어요.</Styled.Description>
        <ReviewButton onClick={() => {}} isActive text="작성한 후기 보러가기" />
        <ReviewButton onClick={() => {}} isActive text="링크 복사하기" />
      </Styled.Wrapper>
    </Styled.Container>
  );
}
