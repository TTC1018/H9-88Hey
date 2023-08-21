import { useState } from 'react';

import * as Styled from './style';

export function SearchBar() {
  const [selectedCarName, setSelectedCar] = useState('전체');

  function handleSelectCar(car: string) {
    setSelectedCar(car);
  }

  const activeCarNames = ['전체', '펠리세이드'];
  const deActiveCarNames = ['베뉴', '코나', '싼타페', '그랜저', '아반떼', '아이오닉'];

  return (
    <Styled.Container>
      {activeCarNames.map(car => (
        <Styled.Wrapper key={car} isActive={car === selectedCarName} onClick={() => handleSelectCar(car)}>
          {car}
        </Styled.Wrapper>
      ))}
      {deActiveCarNames.map(car => (
        <Styled.DeactiveWrapper key={car}>{car}</Styled.DeactiveWrapper>
      ))}
    </Styled.Container>
  );
}
