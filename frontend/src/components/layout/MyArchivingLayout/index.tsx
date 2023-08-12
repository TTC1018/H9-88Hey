import { MyArchiving } from '@/pages/MyArchiving';
import { ArchivingHeader } from '@/components/common/ArchivingHeader';
import { ArchivingNavigation } from '@/components/common/ArchivingNavigation';

import * as style from './style';

export function MyArchivingLayout() {
  return (
    <style.Container>
      <ArchivingHeader />
      <ArchivingNavigation />
      <MyArchiving />
    </style.Container>
  );
}
