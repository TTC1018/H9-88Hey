import { useMyCarContext } from '@/pages/MyArchiving/context';

import * as Styled from './style';

export function MyCarNavigation() {
  const { index, setIndex } = useMyCarContext();

  function handleClick(index: number) {
    setIndex(index);
  }

  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.TitleText onClick={() => handleClick(0)} isActive={index === 0}>
          내가 만든 차량 목록
        </Styled.TitleText>
        <Styled.TitleText onClick={() => handleClick(1)} isActive={index === 1}>
          피드에서 저장한 차량 목록
        </Styled.TitleText>
      </Styled.Wrapper>
      <Styled.Division />
    </Styled.Container>
  );
}
