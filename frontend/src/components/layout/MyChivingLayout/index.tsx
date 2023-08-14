import { MyChiving } from '@/pages/MyChiving';
import { ArchivingHeader } from '@/components/common/ArchivingHeader';
import { ArchivingNavigation } from '@/components/common/ArchivingNavigation';

import * as Styled from './style';

export function MyChivingLayout() {
  return (
    <Styled.Container>
      <ArchivingHeader />
      <ArchivingNavigation />
      <MyChiving />
    </Styled.Container>
  );
}
