import { useRef, useState } from 'react';

import { useFetch } from '@/hooks/useFetch';
import { useInfiniteFetch } from '@/hooks/useInfiniteFetch';
import { useInfiniteScroll } from '@/hooks/useInfiniteScroll';
import { ArchivingCarDataProps } from '@/types/archiving';

import { OptionSearchBar } from '@/components/Archiving/OptionSearchBar';
import { ReviewList } from '@/components/Archiving/ReviewList';
import { SearchBar } from '@/components/Archiving/SearchBar';

import * as Styled from './style';

const carInitialData = {
  selectOptions: [{ id: '', name: '', category: '' }],
};

export function Archiving() {
  const fetchMoreElement = useRef<HTMLDivElement>(null);
  const intersecting = useInfiniteScroll(fetchMoreElement);

  const {
    data: { selectOptions },
  } = useFetch<ArchivingCarDataProps>({
    defaultValue: carInitialData,
    url: '/car/select-options?model_id=1',
  });

  const { data: archivings } = useInfiniteFetch({
    key: 'archivings',
    url: '/archiving?model_id=1&select_option=CO2&select_option=DUP&limit=8',
    defaultValue: [],
    offset: 0,
    intersecting,
  });

  const [selectedOptions, setSelectedOptions] = useState<Set<string>>(new Set());
  const [selectedCarName, setSelectedCar] = useState('전체');

  const selectCarNames = ['전체', '펠리세이드', '베뉴', '코나', '싼타페', '그랜저', '아반떼', '아이오닉'];
  const allOptions = [...new Set(selectOptions.flatMap(item => item.name))];

  // 임시로 자름
  const options = allOptions.slice(0, allOptions.length - 4);

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
        <OptionSearchBar options={options} onSelectOption={handleSelectOption} selectOptions={selectedOptions} />
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
