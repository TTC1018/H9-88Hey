import { ArchivingHeader } from '@/components/common/ArchivingHeader';
import { ArchivingNavigation } from '@/components/common/ArchivingNavigation';
import { MyCarNavigation } from '@/components/archiving/MyCarNavigation';
import { MySavedCar } from '@/components/archiving/MySavedCar';

import * as style from './style';

export function MyArchiving() {
  return (
    <>
      <ArchivingHeader />
      <ArchivingNavigation />
      <style.Container>
        <style.Wrapper>
          <MyCarNavigation />
          <MySavedCar />
        </style.Wrapper>
      </style.Container>
    </>
  );
}
