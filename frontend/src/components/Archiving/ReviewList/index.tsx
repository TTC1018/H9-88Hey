import { useLayoutEffect, useRef } from 'react';

import { ArchivingProps } from '@/types/archiving';
import { updateMasonryLayout } from '@/utils/updateMasonryLayout';

import { ReviewCard } from '@/components/Archiving/ReviewCard';

import * as Styled from './style';

interface Props {
  archivings: ArchivingProps[];
  options: Set<string>;
  onClick: (option: string) => void;
}
export function ReviewList({ archivings, options, onClick }: Props) {
  const masonryRef = useRef<HTMLDivElement>(null);

  useLayoutEffect(() => {
    updateMasonryLayout({ element: masonryRef });
  }, [archivings]);

  return (
    <Styled.Container ref={masonryRef}>
      {archivings.map((archiving, index) => {
        return (
          <Styled.Wrapper key={index}>
            <ReviewCard props={archiving} isArchiving={true} selectedSearchOptions={options} onClick={onClick} />
          </Styled.Wrapper>
        );
      })}
    </Styled.Container>
  );
}
