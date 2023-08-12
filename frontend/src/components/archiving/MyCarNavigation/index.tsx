import { useMyContext } from '@/pages/MyArchiving/context';

import * as style from './style';

export function MyCarNavigation() {
  const { index, setIndex } = useMyContext();

  function handleClick(index: number) {
    setIndex(index);
  }

  return (
    <style.Container>
      <style.Wrapper>
        <style.TitleText onClick={() => handleClick(1)} isActive={index === 1}>
          내가 만든 차량 목록
        </style.TitleText>
        <style.TitleText onClick={() => handleClick(2)} isActive={index === 2}>
          피드에서 저장한 차량 목록
        </style.TitleText>
      </style.Wrapper>
      <style.Division />
    </style.Container>
  );
}
