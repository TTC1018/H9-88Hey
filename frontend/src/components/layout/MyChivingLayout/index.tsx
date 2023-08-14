import { Outlet } from 'react-router-dom';

import { ArchivingHeader } from '@/components/common/ArchivingHeader';
import { ArchivingNavigation } from '@/components/common/ArchivingNavigation';

import * as Styled from './style';

export function MyChivingLayout() {
  return (
    <Styled.Container>
      <ArchivingHeader />
      <ArchivingNavigation />
      <Outlet />
    </Styled.Container>
  );
}
