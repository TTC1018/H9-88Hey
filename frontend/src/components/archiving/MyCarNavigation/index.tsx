import { useState } from 'react';
import * as style from './style';

export default function MyCarNavigation() {
  const [indexNum, setIndexNum] = useState(1);

  function handleClick(index: number) {
    setIndexNum(index);
  }

  return (
    <style.Container>
      <style.Wrapper>
        <style.TitleText onClick={() => handleClick(1)} isActive={indexNum === 1}>
          내가 만든 차량 목록
        </style.TitleText>
        <style.TitleText onClick={() => handleClick(2)} isActive={indexNum === 2}>
          피드에서 저장한 차량 목록
        </style.TitleText>
      </style.Wrapper>
      <style.Division />
    </style.Container>
  );
}
