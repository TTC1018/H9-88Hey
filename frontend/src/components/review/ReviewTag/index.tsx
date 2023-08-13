import * as Styled from './style';

interface Props {
  tags: string[];
}
export function ReviewTag({ tags }: Props) {
  tags; // 아직 안쓰는거
  return (
    <Styled.Container>
      <Styled.TextWrapper>
        <Styled.Medium>태그를 선택해주세요</Styled.Medium>
        <Styled.Regular>(최소 1개 선택필수, 최대 3개 선택 가능)</Styled.Regular>
      </Styled.TextWrapper>
      <Styled.TagWrapper>
        <Styled.Tag isActive>주행을 편안하게 해요🚙</Styled.Tag>
        <Styled.Tag isActive={false}>뒷좌석도 편안해요</Styled.Tag>
        <Styled.Tag isActive={false}>조용한 드라이빙😴</Styled.Tag>
      </Styled.TagWrapper>
    </Styled.Container>
  );
}
