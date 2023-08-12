import { useState } from 'react';

import { ArchivingHeader } from '@/components/common/ArchivingHeader';
import { ArchivingNavigation } from '@/components/common/ArchivingNavigation';
import { MyCarNavigation } from '@/components/archiving/MyCarNavigation';
import { MySavedCar } from '@/components/archiving/MySavedCar';
import { MyFeed } from '@/components/archiving/MyFeed';

import { myContext } from './context';

import * as style from './style';

export function MyArchiving() {
  const [index, setIndex] = useState(1);
  return (
    <>
      <ArchivingHeader />
      <ArchivingNavigation />
      <myContext.Provider value={{ index, setIndex }}>
        <style.Container>
          <style.Wrapper>
            <MyCarNavigation />
            {index === 1 ? <MySavedCar /> : <MyFeed />}
          </style.Wrapper>
        </style.Container>
      </myContext.Provider>
    </>
  );
}
