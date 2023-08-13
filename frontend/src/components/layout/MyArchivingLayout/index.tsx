import { MyArchiving } from '@/pages/MyArchiving';
import { ArchivingHeader } from '@/components/common/ArchivingHeader';
import { ArchivingNavigation } from '@/components/common/ArchivingNavigation';

import * as Styled from './style';

export function MyArchivingLayout() {
  return (
    <Styled.Container>
      <ArchivingHeader />
      <ArchivingNavigation />
      <MyArchiving />
    </Styled.Container>
  );
}
