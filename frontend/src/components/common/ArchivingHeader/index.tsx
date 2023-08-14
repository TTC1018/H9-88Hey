import { useLocation, Link } from 'react-router-dom';

import { ARCHIVING, PATH_LIST } from '@/constants';

import { HyundaiLogo } from '@/components/common/HyundaiLogo';

import * as Styled from './style';

/* ex) 아카이빙: /archiving
       마이카이빙: /my-archiving
*/

type PathType = 'archiving' | 'mychiving';

export function ArchivingHeader() {
  const { pathname } = useLocation();
  const path = pathname.split('/')[1];
  const title = PATH_LIST[path as PathType];

  const isArchivingPage = title === ARCHIVING;

  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Box>
          <HyundaiLogo />
          <Styled.Division />
          <Styled.Text>{title}</Styled.Text>
        </Styled.Box>
        <Styled.Box>
          <Styled.CarNameText>팰리세이드</Styled.CarNameText>
          <Link to={isArchivingPage ? '/mychiving' : '/archiving'}>
            <Styled.Button>{isArchivingPage ? '마이카이빙' : '아카이빙'}</Styled.Button>
          </Link>
        </Styled.Box>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
