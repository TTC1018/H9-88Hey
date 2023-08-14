import { useState } from 'react';

import { MyCarNavigation } from '@/components/myChiving/MyCarNavigation';
import { MySavedCar } from '@/components/myChiving/MySavedCar';
import { MyFeed } from '@/components/myChiving/MyFeed';

import { NavIndexContext } from './context';

import * as Styled from './style';

export function MyArchiving() {
  const [index, setIndex] = useState(0);
  return (
    <NavIndexContext.Provider value={{ index, setIndex }}>
      <Styled.Container>
        <Styled.Wrapper>
          <MyCarNavigation />
          {index === 0 ? <MySavedCar /> : <MyFeed />}
        </Styled.Wrapper>
      </Styled.Container>
    </NavIndexContext.Provider>
  );
}
