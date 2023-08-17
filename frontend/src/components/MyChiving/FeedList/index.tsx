import { Fragment, MouseEvent, useEffect, useRef } from 'react';

import { useModalContext } from '@/hooks/useModalContext';
import { useNavigateWithData } from '@/hooks/useNavigateWithData';
import { MyFeedProps } from '@/types/myChiving';

import { ReviewCard } from '@/components/Archiving/ReviewCard';
import { ModalPortal } from '@/components/common/ModalPortal';
import { PopupModal } from '@/components/common/PopupModal';
import { ModalType } from '@/constants';

import * as Styled from './style';

interface FeedListProps {
  myChivings: MyFeedProps[];
}

interface ClickEventDataProps {
  deleteText: string;
}

export function FeedList({ myChivings }: FeedListProps) {
  const masonryRef = useRef<HTMLDivElement>(null);
  const { handleOpen } = useModalContext();
  const { naviagteWithData } = useNavigateWithData({ path: '/archiving/detail' });

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

  function handleNavigate(review: MyFeedProps) {
    naviagteWithData({ state: review });
  }

  // 목록 제거 함수
  function handleDeleteList() {}

  function handleClick(review: MyFeedProps, data: ClickEventDataProps, event: MouseEvent<HTMLDivElement>) {
    const element = event.target as Element;
    const deleteButton = element.closest('button');

    if (deleteButton) {
      modalInfo.current = { type: ModalType.DELETE, contents: data.deleteText, onClick: handleDeleteList };
      handleOpen();
    } else {
      handleNavigate(review);
    }
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
