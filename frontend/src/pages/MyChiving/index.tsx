import { useState } from 'react';

import { MyCarNavigation } from '@/components/MyChiving/MyCarNavigation';
import { MySavedCar } from '@/components/MyChiving/MySavedCar';
import { MyFeed } from '@/components/MyChiving/MyFeed';

import { NavIndexContext } from './context';

import * as Styled from './style';

export function MyChiving() {
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
