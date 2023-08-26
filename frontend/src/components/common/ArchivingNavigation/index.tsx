import { useLocation, useNavigate } from 'react-router-dom';

import { ARCHIVING, PATH_LIST } from '@/constants';

import { ArchivingLogoDark } from '@/components/common/ArchivingLogoDark';
import { MyArchivingLogoDark } from '@/components/common/MyArchivingLogoDark';
import { PrevButton } from '@/components/common/PrevButton';
import { CarLogo } from '@/components/common/CarLogo';

import * as Styled from './style';

type PathType = 'archiving' | 'mychiving';

export function ArchivingNavigation() {
  const navigate = useNavigate();
  const { pathname } = useLocation();
  const path = pathname.split('/')[1];
  const title = PATH_LIST[path as PathType];

  function handleNavigeToTrim() {
    localStorage.removeItem('myCar');
    localStorage.removeItem('carCode');
    navigate('/trim');
  }

  return (
    <Styled.Container>
      <Styled.Wrapper>
        <PrevButton width="48" height="48" onClick={() => navigate(-1)} />
        <Styled.TitleBox>
          {title === ARCHIVING ? <ArchivingLogoDark /> : <MyArchivingLogoDark />}
          <Styled.TitleText>{title}</Styled.TitleText>
        </Styled.TitleBox>
        <Styled.ButtonBox onClick={handleNavigeToTrim}>
          <CarLogo />
          <Styled.ButtonText>내 차 만들기 바로가기</Styled.ButtonText>
        </Styled.ButtonBox>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
