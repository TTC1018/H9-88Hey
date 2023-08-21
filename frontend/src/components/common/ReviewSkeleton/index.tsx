import * as Styled from './style';

export function ReviewSkeleton() {
  return (
    <Styled.Container>
      <Styled.TagWrapper>
        <Styled.TagSkeleton></Styled.TagSkeleton>
        <Styled.TagSkeleton></Styled.TagSkeleton>
      </Styled.TagWrapper>
      <Styled.TagWrapper>
        <Styled.TagSkeleton></Styled.TagSkeleton>
        <Styled.TagSkeleton></Styled.TagSkeleton>
      </Styled.TagWrapper>
    </Styled.Container>
  );
}
