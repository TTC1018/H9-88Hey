import { useNavigateWithData } from '@/hooks/useNavigateWithData';
import { MyFeedProps } from '@/types/myChiving';

import { ReviewCard } from '@/components/Archiving/ReviewCard';

import * as Styled from './style';
import { useEffect, useRef } from 'react';

interface Props {
  myChivings: MyFeedProps[];
}

export function FeedList({ myChivings }: Props) {
  const masonryRef = useRef<HTMLDivElement>(null);

  const { naviagteWithData } = useNavigateWithData({ path: '/archiving/detail' });

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

  return (
    <Styled.Container ref={masonryRef}>
      {myChivings.map((review, index) => {
        return (
          <Styled.Wrapper key={index} onClick={() => naviagteWithData({ state: review })}>
            <ReviewCard props={review} isArchiving={false} onClick={() => {}} />
          </Styled.Wrapper>
        );
      })}
    </Styled.Container>
  );
}
