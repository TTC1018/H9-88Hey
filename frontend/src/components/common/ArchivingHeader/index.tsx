import { useLocation, Link } from 'react-router-dom';

import { ARCHIVING, PATH_LIST } from '@/constants';

import { HyundaiLogo } from '@/components/common/HyundaiLogo';

import * as style from './style';

/* ex) 아카이빙: /archiving
       마이카이빙: /my-archiving
*/

type PathType = 'archiving' | 'my-archiving';

export function ArchivingHeader() {
  const { pathname } = useLocation();
  const path = pathname.split('/')[1];
  const title = PATH_LIST[path as PathType];

  function isArchivingPage() {
    return title === ARCHIVING;
  }

  return (
    <style.Container>
      <style.Wrapper>
        <style.Box>
          <HyundaiLogo />
          <style.Division />
          <style.Text>{title}</style.Text>
        </style.Box>
        <style.Box>
          <style.CarNameText>팰리세이드</style.CarNameText>
          <Link to={isArchivingPage() ? '/my-archiving' : '/archiving'}>
            <style.Button>{isArchivingPage() ? '마이카이빙' : '아카이빙'}</style.Button>
          </Link>
        </style.Box>
      </style.Wrapper>
    </style.Container>
  );
}
