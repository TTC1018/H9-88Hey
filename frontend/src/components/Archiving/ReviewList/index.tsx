import { useEffect, useRef } from 'react';

import { ArchivingProps } from '@/types/archiving';
import { useNavigateWithData } from '@/hooks/useNavigateWithData';

import { ReviewCard } from '@/components/Archiving/ReviewCard';

import * as Styled from './style';

interface Props {
  archivings: ArchivingProps[];
  options: Set<string>;
  onClick: (option: string) => void;
}
export function ReviewList({ archivings, options, onClick }: Props) {
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
  }, [archivings]);

  return (
    <Styled.Container ref={masonryRef}>
      {archivings.map((review, index) => {
        return (
          <Styled.Wrapper key={index} onClick={() => naviagteWithData({ state: review })}>
            <ReviewCard props={review} isArchiving={true} selectedSearchOptions={options} onClick={onClick} />
          </Styled.Wrapper>
        );
      })}
    </Styled.Container>
  );
}
