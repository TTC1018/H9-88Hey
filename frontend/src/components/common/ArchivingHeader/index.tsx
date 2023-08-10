import { HyundaiLogo } from '@/components/common/HyundaiLogo';

import * as style from './style';

export function ArchivingHeader() {
  return (
    <style.Container>
      <style.Wrapper>
        <style.Box>
          <HyundaiLogo />
          <style.Division />
          <style.Text>아카이빙</style.Text>
        </style.Box>
        <style.Box>
          <style.CarNameText>팰리세이드</style.CarNameText>
          <style.Division />
          <style.Button>마이카이빙</style.Button>
        </style.Box>
      </style.Wrapper>
    </style.Container>
  );
}
