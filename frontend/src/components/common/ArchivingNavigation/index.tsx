import { ArchivingLogo } from '@/components/common/ArchivingLogo';
import * as style from './style';
import { PrevButton } from '../PrevButton';
import { CarLogo } from '../CarLogo';

export function ArchivingNavigation() {
  return (
    <style.Container>
      <style.Wrapper>
        <PrevButton width="48" height="48" onClick={() => {}} />
        <style.TitleBox>
          <ArchivingLogo />
          <style.TitleText>아카이빙</style.TitleText>
        </style.TitleBox>
        <style.ButtonBox>
          <CarLogo />
          <style.ButtonText>내 차 만들기 바로가기</style.ButtonText>
        </style.ButtonBox>
      </style.Wrapper>
    </style.Container>
  );
}
