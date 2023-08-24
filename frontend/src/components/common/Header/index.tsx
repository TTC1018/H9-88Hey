import { Fragment } from 'react';

import { useLocation } from 'react-router-dom';

import { ModalType } from '@/constants';

import { useModalContext } from '@/hooks/useModalContext';
import { useMyCarNavigate } from '@/hooks/useMyCarNavigate';

import { PopupModal } from '@/components/common/PopupModal';
import { ModalPortal } from '@/components/common/ModalPortal';
import { HyundaiLogo } from '@/components/common/HyundaiLogo';
import { ArchivingLogo } from '@/components/common/ArchivingLogo';
import { AutoSavingLogo } from '@/components/common/AutoSavingLogo';

import * as Styled from './style';

interface Props {
  isSaving: boolean;
}

export function Header({ isSaving }: Props) {
  const { handleOpen } = useModalContext();
  const { handleNavigate } = useMyCarNavigate({ path: '/archiving' });

  return (
    <Fragment>
      <Styled.Container>
        <Styled.Wrapper>
          <Styled.Box>
            <HyundaiLogo />
            <Styled.Division />
            <Styled.Text>내차 만들기</Styled.Text>
          </Styled.Box>
          <Styled.ButtonWrapper>
            <Styled.InfoBox>
              <Styled.AutoSavingBox isDisplay={isSaving}>
                <Styled.AutoSavingText>자동저장 중</Styled.AutoSavingText>
                <AutoSavingLogo />
              </Styled.AutoSavingBox>
              <Styled.CarNameText>팰리세이드</Styled.CarNameText>
            </Styled.InfoBox>
            <Styled.Division />
            <Styled.ButtonBox onClick={handleOpen}>
              <ArchivingLogo />
              <Styled.ButtonText>아카이빙</Styled.ButtonText>
            </Styled.ButtonBox>
          </Styled.ButtonWrapper>
        </Styled.Wrapper>
      </Styled.Container>
      <ModalPortal>
        <PopupModal type={ModalType.CLOSE} onClick={handleNavigate} />
      </ModalPortal>
    </Fragment>
  );
}
