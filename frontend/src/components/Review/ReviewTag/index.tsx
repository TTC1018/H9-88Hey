import * as Styled from './style';

interface Props {
  tags: string[];
  selectedIndex: Set<string>;
  onClick: (tag: string) => void;
}
export function ReviewTag({ tags, selectedIndex, onClick }: Props) {
  return (
    <Styled.Container>
      <Styled.TextWrapper>
        <Styled.Medium>태그를 선택해주세요</Styled.Medium>
        <Styled.Regular>(최소 1개 선택필수, 최대 3개 선택 가능)</Styled.Regular>
      </Styled.TextWrapper>
      <Styled.TagWrapper>
        {tags.map((tag, index) => (
          <Styled.Tag key={index} isActive={selectedIndex.has(tag)} onClick={() => onClick(tag)}>
            {tag}
          </Styled.Tag>
        ))}
      </Styled.TagWrapper>
    </Styled.Container>
  );
}
