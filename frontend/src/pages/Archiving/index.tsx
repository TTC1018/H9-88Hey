import { useRef, useState } from 'react';

import { ArchivingProps } from '@/types/archiving';
import { apiPath } from '@/constants';
import { useInfiniteFetch } from '@/hooks/useInfiniteFetch';
import { useInfiniteScroll } from '@/hooks/useInfiniteScroll';

import { OptionSearchBar } from '@/components/Archiving/OptionSearchBar';
import { ReviewList } from '@/components/Archiving/ReviewList';
import { SearchBar } from '@/components/Archiving/SearchBar';

import * as Styled from './style';

export function Archiving() {
  const [selectedOptions, setSelectedOptions] = useState<Set<string>>(new Set());
  const fetchMoreElement = useRef<HTMLDivElement>(null);
  const intersecting = useInfiniteScroll(fetchMoreElement);
  const nextOffset = useRef(1);

  const { data: archivings } = useInfiniteFetch<ArchivingProps>({
    key: 'archivings',
    url: apiPath.archiving(1, Array.from(selectedOptions), 8, nextOffset.current),
    intersecting,
    nextOffset,
    dependencies: Array.from(selectedOptions),
  });

  const [selectedCarName, setSelectedCar] = useState('전체');

  const selectCarNames = ['전체', '펠리세이드', '베뉴', '코나', '싼타페', '그랜저', '아반떼', '아이오닉'];

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

  function handleSelectCar(car: string) {
    setSelectedCar(car);
  }

  return (
    <Styled.Container>
      <Styled.HeaderWrapper>
        <SearchBar selectedCar={selectedCarName} onClick={handleSelectCar} cars={selectCarNames} />
        <OptionSearchBar onSelectOption={handleSelectOption} selectedOptions={selectedOptions} />
      </Styled.HeaderWrapper>
      <Styled.ReviewWrapper>
        {archivings.length === 0 ? (
          <Styled.InfoBox>조건에 맞는 결과가 없습니다.</Styled.InfoBox>
        ) : (
          <ReviewList archivings={archivings} options={selectedOptions} onClick={handleSelectOption} />
        )}
      </Styled.ReviewWrapper>
      <div ref={fetchMoreElement} />
    </Styled.Container>
  );
}
