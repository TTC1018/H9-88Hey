import { useContext } from 'react';

import { useLocation, Link, useNavigate } from 'react-router-dom';

import { ARCHIVING, PATH_LIST, ModalType } from '@/constants';
import { useModalContext } from '@/hooks/useModalContext';
import { removeLocalStorage } from '@/utils';

import { HyundaiLogo } from '@/components/common/HyundaiLogo';
import { AuthContext } from '@/AuthProvider';
import { PopupModal } from '@/components/common/PopupModal';
import { ModalPortal } from '@/components/common/ModalPortal';

import * as Styled from './style';

type PathType = 'archiving' | 'mychiving';

export function ArchivingHeader() {
  const { pathname } = useLocation();
  const path = pathname.split('/')[1];
  const title = PATH_LIST[path as PathType];

  const isArchivingPage = title === ARCHIVING;

  const { userName, setIsSignin } = useContext(AuthContext);

  const { handleOpen } = useModalContext();

  const navigate = useNavigate();

  function handleSignout() {
    setIsSignin(false);
    removeLocalStorage('accessToken');
    removeLocalStorage('refreshToken');
    navigate('/', { replace: true });
  }

  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Box>
          <HyundaiLogo />
          <Styled.Division />
          <Styled.Text>{title}</Styled.Text>
          <Styled.CarNameText>팰리세이드</Styled.CarNameText>
        </Styled.Box>
        <Styled.Box>
          <Styled.Enclosure>
            <Styled.UserNameText>{userName}</Styled.UserNameText>
            <Styled.GreetingText>님, 안녕하세요!</Styled.GreetingText>
          </Styled.Enclosure>
          <Styled.LogoutButton onClick={handleOpen}>로그아웃</Styled.LogoutButton>
          <Link to={isArchivingPage ? '/mychiving' : '/archiving'}>
            <Styled.Button>{isArchivingPage ? '마이카이빙' : '아카이빙'}</Styled.Button>
          </Link>
        </Styled.Box>
      </Styled.Wrapper>
      <ModalPortal>
        <PopupModal type={ModalType.SIGNOUT} onClick={handleSignout} />
      </ModalPortal>
    </Styled.Container>
  );
}
