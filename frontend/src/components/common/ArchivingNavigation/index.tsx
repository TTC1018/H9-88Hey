import { useLocation } from 'react-router-dom';

import { PATH_LIST } from '@/constants';

import { ArchivingLogoDark } from '@/components/common/ArchivingLogoDark';
import { PrevButton } from '@/components/common/PrevButton';
import { CarLogo } from '@/components/common/CarLogo';

import * as style from './style';
import { Link } from 'react-router-dom';

type PathType = 'archiving' | 'my-archiving';

export function ArchivingNavigation() {
  const { pathname } = useLocation();
  const path = pathname.split('/')[1];
  const title = PATH_LIST[path as PathType];

  return (
    <style.Container>
      <style.Wrapper>
        <PrevButton width="48" height="48" onClick={() => {}} />
        <style.TitleBox>
          <ArchivingLogoDark />
          <style.TitleText>{title}</style.TitleText>
        </style.TitleBox>
        <Link to={'/trim'}>
          <style.ButtonBox>
            <CarLogo />
            <style.ButtonText>내 차 만들기 바로가기</style.ButtonText>
          </style.ButtonBox>
        </Link>
      </style.Wrapper>
    </style.Container>
  );
}
