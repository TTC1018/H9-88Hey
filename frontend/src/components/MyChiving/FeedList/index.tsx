import { Fragment, MouseEvent, useEffect, useRef } from 'react';

import { useNavigate } from 'react-router-dom';

import { useModalContext } from '@/hooks/useModalContext';

import { ReviewCard } from '@/components/Archiving/ReviewCard';
import { ModalPortal } from '@/components/common/ModalPortal';
import { PopupModal } from '@/components/common/PopupModal';
import { ModalType } from '@/constants';

import * as Styled from './style';
import { ArchivingProps } from '@/types/archiving';

interface FeedListProps {
  myFeedChiving: ArchivingProps[];
  onDelete: (id: string, key: 'feedId' | 'myChivingId') => void;
}

interface ClickEventDataProps {
  deleteText: string;
  moveText: string;
}

export function FeedList({ myFeedChiving, onDelete }: FeedListProps) {
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
  }, [myFeedChiving]);

  const navigate = useNavigate();

  function handleDeleteList(review: ArchivingProps) {
    onDelete(review.feedId, 'feedId');
  }

  function handleNavigate(review: ArchivingProps) {
    navigate(`/archiving/detail?feed_id=${review.feedId}`, { state: review });
  }

  function handleClick(review: ArchivingProps, data: ClickEventDataProps, event: MouseEvent<HTMLDivElement>) {
    const element = event.target as Element;
    const deleteButton = element.closest('button');

    if (deleteButton) {
      modalInfo.current = {
        type: ModalType.DELETE,
        contents: data.deleteText,
        onClick: () => handleDeleteList(review),
      };
    } else {
      modalInfo.current = { type: ModalType.MOVE, contents: data.moveText, onClick: () => handleNavigate(review) };
    }
    handleOpen();
  }

  return (
    <Fragment>
      <Styled.Container ref={masonryRef}>
        {myFeedChiving.map((review, index) => {
          return (
            <Styled.Wrapper key={index}>
              <ReviewCard props={review} isArchiving={false} onClick={handleClick} onDelete={onDelete} />
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
