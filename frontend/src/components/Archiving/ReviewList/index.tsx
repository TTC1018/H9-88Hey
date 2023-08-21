import { useLayoutEffect, useRef } from 'react';

import { ArchivingProps } from '@/types/archiving';
import { updateMasonryLayout } from '@/utils/updateMasonryLayout';

import { ReviewCard } from '@/components/Archiving/ReviewCard';

import * as Styled from './style';

interface Props {
  archivings: ArchivingProps[];
  isLoading: boolean;
  options: Set<string>;
  onClick: (option: string) => void;
}
export function ReviewList({ archivings, isLoading, options, onClick }: Props) {
  const masonryRef = useRef<HTMLDivElement>(null);

  useLayoutEffect(() => {
    updateMasonryLayout({ element: masonryRef });
  }, [archivings]);

  return (
    <Styled.Container ref={masonryRef}>
      {archivings.map((archiving, index) => (
        <Styled.Wrapper key={index}>
          <ReviewCard props={archiving} isArchiving={true} selectedSearchOptions={options} onClick={onClick} />
        </Styled.Wrapper>
      ))}
      {isLoading && (
        <Styled.Wrapper>
          <Styled.Loading></Styled.Loading>
        </Styled.Wrapper>
      )}
    </Styled.Container>
  );
}
