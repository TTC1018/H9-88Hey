import { useState } from 'react';

import { MyCarNavigation } from '@/components/archiving/MyCarNavigation';
import { MySavedCar } from '@/components/archiving/MySavedCar';
import { MyFeed } from '@/components/archiving/MyFeed';

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
