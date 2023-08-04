import { HyundaiLogo } from '@/components/common/HyundaiLogo/index.tsx';
import { ArchivingLogo } from '@/components/common/ArchivingLogo/index.tsx';
import { AutoSavingLogo } from '@/components/common/AutoSavingLogo/index.tsx';

import * as style from './style';

export function Header() {
  return (
    <style.Container>
      <style.Wrapper>
        <HyundaiLogo />
        <style.Division />
        <style.Text>내차 만들기</style.Text>
      </style.Wrapper>
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
    </style.Container>
  );
}
