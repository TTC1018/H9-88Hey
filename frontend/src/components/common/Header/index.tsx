import { HyundaiLogo } from '@/components/common/HyundaiLogo';
import { ArchivingLogo } from '@/components/common/ArchivingLogo';
import { AutoSavingLogo } from '@/components/common/AutoSavingLogo';

import * as style from './style';

export function Header() {
  return (
    <style.Container>
      <style.Wrapper>
        <style.Box>
          <HyundaiLogo />
          <style.Division />
          <style.Text>내차 만들기</style.Text>
        </style.Box>
        <style.ButtonWrapper>
          <style.InfoBox>
            <style.AutoSavingBox>
              <style.AutoSavingText>자동저장 중</style.AutoSavingText>
              <AutoSavingLogo />
            </style.AutoSavingBox>
            <style.CarNameText>팰리세이드</style.CarNameText>
          </style.InfoBox>
          <style.Division />
          <style.ButtonBox>
            <ArchivingLogo />
            <style.ButtonText>아카이빙</style.ButtonText>
          </style.ButtonBox>
        </style.ButtonWrapper>
      </style.Wrapper>
    </style.Container>
  );
}
