import { Suspense } from 'react';

import { Outlet } from 'react-router-dom';

import { Loading } from '@/components/common/Loading';
import { ArchivingHeader } from '@/components/common/ArchivingHeader';
import { ArchivingNavigation } from '@/components/common/ArchivingNavigation';

import * as Styled from './style';

export function MyChivingLayout() {
  return (
    <Styled.Container>
      <ArchivingHeader />
      <ArchivingNavigation />
      <Suspense fallback={<Loading />}>
        <Outlet />
      </Suspense>
    </Styled.Container>
  );
}
