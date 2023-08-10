import { HyundaiLogo } from '@/components/common/HyundaiLogo';
import * as style from './style';

export function ArchivingHeader() {
  return (
    <style.Container>
      <style.Wrapper>
        <HyundaiLogo />
        <style.Division />
        <style.Text>내차 만들기</style.Text>
      </style.Wrapper>
      <style.Wrapper>
        <style.CarNameText>팰리세이드</style.CarNameText>
        <style.Division />
        <style.Button>마이카이빙</style.Button>
      </style.Wrapper>
    </style.Container>
  );
}
