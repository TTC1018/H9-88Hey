import * as Styled from './style';

interface Props {
  onClick: (index: number) => void;
  index: number;
}

export function MyCarNavigation({ onClick, index }: Props) {
  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.TitleText onClick={() => onClick(0)} isActive={index === 0}>
          내가 만든 차량 목록
        </Styled.TitleText>
        <Styled.TitleText onClick={() => onClick(1)} isActive={index === 1}>
          피드에서 저장한 차량 목록
        </Styled.TitleText>
      </Styled.Wrapper>
      <Styled.Division />
    </Styled.Container>
  );
}
