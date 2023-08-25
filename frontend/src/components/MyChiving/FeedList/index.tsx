import { Fragment, useEffect, useRef } from 'react';

import { ArchivingProps } from '@/types/archiving';

import { ReviewCard } from '@/components/Archiving/ReviewCard';
import { ModalPortal } from '@/components/common/ModalPortal';
import { PopupModal } from '@/components/common/PopupModal';
import { ModalType } from '@/constants';

import * as Styled from './style';

interface FeedListProps {
  myFeedChiving: ArchivingProps[];
  onDelete: (id: string, key: 'feedId' | 'myChivingId') => void;
}

export function FeedList({ myFeedChiving, onDelete }: FeedListProps) {
  const masonryRef = useRef<HTMLDivElement>(null);

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

  return (
    <Fragment>
      <Styled.Container ref={masonryRef}>
        {myFeedChiving.map((review, index) => {
          return (
            <Styled.Wrapper key={index}>
              <ReviewCard props={review} isArchiving={false} onClickXButton={onDelete} />
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
