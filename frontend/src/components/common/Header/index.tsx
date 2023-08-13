import { ModalTypeProps } from '@/constants';

import { useModalContext } from '@/hooks/useModalContext';

import { HyundaiLogo } from '@/components/common/HyundaiLogo';
import { ArchivingLogo } from '@/components/common/ArchivingLogo';
import { AutoSavingLogo } from '@/components/common/AutoSavingLogo';

import * as Styled from './style';

export function Header() {
  const { handleOpen } = useModalContext();

  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Box>
          <HyundaiLogo />
          <Styled.Division />
          <Styled.Text>내차 만들기</Styled.Text>
        </Styled.Box>
        <Styled.ButtonWrapper>
          <Styled.InfoBox>
            <Styled.AutoSavingBox>
              <Styled.AutoSavingText>자동저장 중</Styled.AutoSavingText>
              <AutoSavingLogo />
            </Styled.AutoSavingBox>
            <Styled.CarNameText>팰리세이드</Styled.CarNameText>
          </Styled.InfoBox>
          <Styled.Division />
          <Styled.ButtonBox
            onClick={() => {
              handleOpen({ modalType: ModalTypeProps.CLOSE, callbackData: null });
            }}
          >
            <ArchivingLogo />
            <Styled.ButtonText>아카이빙</Styled.ButtonText>
          </Styled.ButtonBox>
        </Styled.ButtonWrapper>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
