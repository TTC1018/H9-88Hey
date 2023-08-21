import { Link, useLocation, useNavigate } from 'react-router-dom';

import { PATH_LIST } from '@/constants';

import { ArchivingLogoDark } from '@/components/common/ArchivingLogoDark';
import { PrevButton } from '@/components/common/PrevButton';
import { CarLogo } from '@/components/common/CarLogo';

import * as Styled from './style';

type PathType = 'archiving' | 'mychiving';

export function ArchivingNavigation() {
  const navigate = useNavigate();
  const { pathname } = useLocation();
  const path = pathname.split('/')[1];
  const title = PATH_LIST[path as PathType];

  return (
    <Styled.Container>
      <Styled.Wrapper>
        <PrevButton width="48" height="48" onClick={() => navigate(-1)} />
        <Styled.TitleBox>
          <ArchivingLogoDark />
          <Styled.TitleText>{title}</Styled.TitleText>
        </Styled.TitleBox>
        <Link to={'/trim'}>
          <Styled.ButtonBox>
            <CarLogo />
            <Styled.ButtonText>내 차 만들기 바로가기</Styled.ButtonText>
          </Styled.ButtonBox>
        </Link>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
