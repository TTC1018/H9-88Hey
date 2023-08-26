import { useRef, useState } from 'react';

import { ArchivingProps } from '@/types/archiving';
import { apiPath } from '@/constants';
import { useInfiniteFetch } from '@/hooks/useInfiniteFetch';
import { useInfiniteScroll } from '@/hooks/useInfiniteScroll';
import { useShowScrollButton } from '@/hooks/useShowScrollButton';

import { SearchBar } from '@/components/Archiving/SearchBar';
import { ReviewList } from '@/components/Archiving/ReviewList';
import { ReviewSkeleton } from '@/components/common/ReviewSkeleton';
import { ScrollTopButton } from '@/components/common/ScrollTopButton';
import { OptionSearchBar } from '@/components/Archiving/OptionSearchBar';

import * as Styled from './style';
import { EmptyContent } from '@/components/common/EmptyContent';

export function Archiving() {
  const [selectedOptions, setSelectedOptions] = useState<Set<string>>(new Set());
  const fetchMoreElement = useRef<HTMLDivElement>(null);
  const intersecting = useInfiniteScroll(fetchMoreElement);
  const nextOffset = useRef(1);
  const { isShow, scrollToTop } = useShowScrollButton({ scrollY: 800, scrollTo: 0 });

  const {
    data: archivings,
    isLoading,
    hasNext,
  } = useInfiniteFetch<ArchivingProps>({
    key: 'archivings',
    url: apiPath.archiving(1, Array.from(selectedOptions), 16, nextOffset.current),
    intersecting,
    nextOffset,
    dependencies: Array.from(selectedOptions),
  });

  function handleSelectOption(option: string) {
    setSelectedOptions(prev => {
      if (prev.has(option)) {
        const newSet = new Set(prev);
        newSet.delete(option);

        return newSet;
      }

      return new Set([...prev, option]);
    });
  }

  return (
    <Styled.Container>
      <Styled.HeaderWrapper>
        <SearchBar />
        <OptionSearchBar onSelectOption={handleSelectOption} selectedOptions={selectedOptions} />
      </Styled.HeaderWrapper>
      <Styled.ReviewWrapper>
        {isLoading && <ReviewSkeleton />}
        {!hasNext && archivings.length === 0 && <EmptyContent />}
        {!isLoading && archivings.length > 0 && (
          <ReviewList
            isLoading={isLoading}
            archivings={archivings}
            options={selectedOptions}
            onClick={handleSelectOption}
          />
        )}
        {isShow && <ScrollTopButton onClick={scrollToTop} />}
      </Styled.ReviewWrapper>
      <div ref={fetchMoreElement} />
    </Styled.Container>
  );
}
