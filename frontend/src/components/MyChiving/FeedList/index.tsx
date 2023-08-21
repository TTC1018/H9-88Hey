import { Fragment, useEffect, useRef } from 'react';

import { useModalContext } from '@/hooks/useModalContext';

import { ReviewCard } from '@/components/Archiving/ReviewCard';
import { ModalPortal } from '@/components/common/ModalPortal';
import { PopupModal } from '@/components/common/PopupModal';
import { ModalType } from '@/constants';

import * as Styled from './style';
import { ArchivingProps } from '@/types/archiving';

interface FeedListProps {
  myChivings: ArchivingProps[];
}

export function FeedList({ myChivings }: FeedListProps) {
  const masonryRef = useRef<HTMLDivElement>(null);
  const { handleOpen } = useModalContext();

  const modalInfo = useRef({
    type: ModalType.CLOSE,
    contents: '',
    onClick: () => {},
  });

  function masonryLayout() {
    const masonryItems = masonryRef.current?.childNodes;

    masonryItems?.forEach(item => {
      const element = item as HTMLElement;
      const innerElement = element.children[0];
      const innerElementHeight = innerElement.getBoundingClientRect().height;

      element.style.gridRowEnd = `span ${Math.ceil((innerElementHeight + 10) / 50)}`;
    });
  }

  useEffect(() => {
    masonryLayout();
  }, [myChivings]);

  // 목록 제거 함수
  function handleDeleteList() {}

  function handleClick(contents: string) {
    modalInfo.current = { type: ModalType.DELETE, contents, onClick: handleDeleteList };
    handleOpen();
  }

  return (
    <Fragment>
      <Styled.Container ref={masonryRef}>
        {myChivings.map((review, index) => {
          return (
            <Styled.Wrapper key={index}>
              <ReviewCard props={review} isArchiving={false} onClick={handleClick} />
            </Styled.Wrapper>
          );
        })}
      </Styled.Container>
      <ModalPortal>
        <PopupModal
          type={modalInfo.current.type}
          onClick={modalInfo.current.onClick}
          contents={modalInfo.current.contents}
        />
      </ModalPortal>
    </Fragment>
  );
}
