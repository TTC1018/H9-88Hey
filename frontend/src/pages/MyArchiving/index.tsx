import { useState } from 'react';

import { MyCarNavigation } from '@/components/archiving/MyCarNavigation';
import { MySavedCar } from '@/components/archiving/MySavedCar';
import { MyFeed } from '@/components/archiving/MyFeed';

import { NavIndexContext } from './context';

import * as style from './style';

export function MyArchiving() {
  const [index, setIndex] = useState(1);
  return (
    <NavIndexContext.Provider value={{ index, setIndex }}>
      <style.Container>
        <style.Wrapper>
          <MyCarNavigation />
          {index === 1 ? <MySavedCar /> : <MyFeed />}
        </style.Wrapper>
      </style.Container>
    </NavIndexContext.Provider>
  );
}
