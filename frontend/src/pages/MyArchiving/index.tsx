import { MyCarList } from '@/components/archiving/MyCarList';
import { ArchivingHeader } from '@/components/common/ArchivingHeader';
import { ArchivingNavigation } from '@/components/common/ArchivingNavigation';

import * as style from './style';

export function MyArchiving() {
  return (
    <>
      <ArchivingHeader />
      <ArchivingNavigation />
      <style.Container>
        <style.Wrapper>
          <style.TitleText>내가 만든 차량 목록</style.TitleText>
          <style.Division />
          <style.MyCarBox>
            <MyCarList />
            <MyCarList />
            <MyCarList />
            <MyCarList />
          </style.MyCarBox>
        </style.Wrapper>
      </style.Container>
    </>
  );
}
