import * as Styled from './style';

interface Props {
  count: number;
}
export function TagSkeleton({ count }: Props) {
  const tagCount = Array.from({ length: count }, (_, index) => index);

  return (
    <Styled.Container>
      {tagCount.map(tag => (
        <Styled.TagSkeleton key={tag}></Styled.TagSkeleton>
      ))}
    </Styled.Container>
  );
}
