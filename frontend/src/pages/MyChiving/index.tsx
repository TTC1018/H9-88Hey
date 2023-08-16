import { Fragment, useState } from 'react';

import { ModalPortal } from '@/components/common/ModalPortal';
import { PopupModal } from '@/components/common/PopupModal';
import { MyCarNavigation } from '@/components/MyChiving/MyCarNavigation';
import { MySavedCar } from '@/components/MyChiving/MySavedCar';
import { MyFeed } from '@/components/MyChiving/MyFeed';

import * as Styled from './style';

export function MyChiving() {
  const [index, setIndex] = useState(0);

  function handleSetIndex(index: number) {
    setIndex(index);
  }

  return (
    <Fragment>
      <Styled.Container>
        <Styled.Wrapper>
          <MyCarNavigation onClick={handleSetIndex} index={index} />
          {index === 0 ? <MySavedCar /> : <MyFeed />}
        </Styled.Wrapper>
      </Styled.Container>
      <ModalPortal>
        <PopupModal />
      </ModalPortal>
    </Fragment>
  );
}
