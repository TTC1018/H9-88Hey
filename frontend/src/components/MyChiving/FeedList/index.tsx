import { Fragment, useEffect, useRef } from 'react';

import { ArchivingProps } from '@/types/archiving';

import { ReviewCard } from '@/components/Archiving/ReviewCard';

import * as Styled from './style';

interface FeedListProps {
  myChivingFeed: ArchivingProps[];
  onDelete: (id: string, key: 'feedId' | 'myChivingId') => void;
}

export function FeedList({ myChivingFeed, onDelete }: FeedListProps) {
  const masonryRef = useRef<HTMLDivElement>(null);

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
  }, [myChivingFeed]);

  return (
    <Fragment>
      <Styled.Container ref={masonryRef}>
        {myChivingFeed.map((review, index) => {
          return (
            <Styled.Wrapper key={index}>
              <ReviewCard props={review} isArchiving={false} onClick={onDelete} />
            </Styled.Wrapper>
          );
        })}
      </Styled.Container>
    </Fragment>
  );
}
