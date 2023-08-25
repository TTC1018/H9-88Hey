import { Fragment, useEffect, useState, useContext } from 'react';

import { useLocation, useNavigate } from 'react-router-dom';

import { removeLocalStorage } from '@/utils';

import { PopupModal } from '@/components/common/PopupModal';
import { ModalPortal } from '@/components/common/ModalPortal';
import { HyundaiLogo } from '@/components/common/HyundaiLogo';
import { ArchivingLogo } from '@/components/common/ArchivingLogo';
import { AutoSavingLogo } from '@/components/common/AutoSavingLogo';
import { AuthContext } from '@/AuthProvider';

import * as Styled from './style';
import { useModalContext } from '@/hooks/useModalContext';
import { useMyCarNavigate } from '@/hooks/useMyCarNavigate';
import { ModalType } from '@/constants';

interface ModalInfoProps {
  [key: string]: any;
}

export function Header() {
  const { pathname } = useLocation();
  const [isSavingNow, setIsSavingNow] = useState(false);

  const [modalType, setModalType] = useState('');

  const { handleOpen } = useModalContext();
  const { handleNavigate } = useMyCarNavigate({ path: '/archiving' });

  const { setIsSignin, userName } = useContext(AuthContext);

  if (isSavingNow) {
    setTimeout(() => {
      setIsSavingNow(false);
    }, 2000);
  }

  useEffect(() => {
    if (pathname !== '/result') {
      setIsSavingNow(true);
    }
  }, [pathname]);

  const navigate = useNavigate();

  const modalInfo: ModalInfoProps = {
    CLOSE: {
      type: ModalType.CLOSE,
      onClick: handleNavigate,
    },
    SIGNOUT: {
      type: ModalType.SIGNOUT,
      onClick: handleSignout,
    },
  };

  function handleSignout() {
    setIsSignin(false);
    removeLocalStorage('accessToken');
    removeLocalStorage('refreshToken');
    navigate('/', { replace: true });
  }

  function handleOpenModal(modalType: string) {
    setModalType(modalType);
    handleOpen();
  }

  return (
    <Fragment>
      <Styled.Container>
        <Styled.Wrapper>
          <Styled.Box>
            <HyundaiLogo />
            <Styled.Division />
            <Styled.Text>내차 만들기</Styled.Text>
            <Styled.CarNameText>팰리세이드</Styled.CarNameText>
          </Styled.Box>
          <Styled.ButtonWrapper>
            <Styled.InfoBox>
              <Styled.AutoSavingBox isDisplay={isSavingNow}>
                <Styled.AutoSavingText>자동저장 중</Styled.AutoSavingText>
                <AutoSavingLogo />
              </Styled.AutoSavingBox>
            </Styled.InfoBox>
            <Styled.Division />
            <Styled.UserNameText>{userName}</Styled.UserNameText>
            <Styled.GreetingText>님, 안녕하세요!</Styled.GreetingText>
            <Styled.LogoutButton onClick={() => handleOpenModal('SIGNOUT')}>로그아웃</Styled.LogoutButton>
            <Styled.ButtonBox onClick={() => handleOpenModal('CLOSE')}>
              <ArchivingLogo />
              <Styled.ButtonText>아카이빙</Styled.ButtonText>
            </Styled.ButtonBox>
          </Styled.ButtonWrapper>
        </Styled.Wrapper>
      </Styled.Container>
      <ModalPortal>
        {modalType !== '' && <PopupModal type={modalInfo[modalType].type} onClick={modalInfo[modalType].onClick} />}
      </ModalPortal>
    </Fragment>
  );
}
