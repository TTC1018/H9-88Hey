import { useState } from 'react';

import { useFetch } from '@/hooks/useFetch';
import { ArchivingCarDataProps, ArchivingDataProps } from '@/types/archiving';

import { OptionSearchBar } from '@/components/Archiving/OptionSearchBar';
import { SearchBar } from '@/components/Archiving/SearchBar';

import * as style from './style';
import { ReviewList } from '@/components/Archiving/ReviewList';

const initialData = {
  archivings: [
    {
      id: 1,
      model: '',
      trim: '',
      isPurchase: false,
      trimOptions: [''],
      interiorColor: '',
      exteriorColor: '',
      creationDate: '',
      selectedOptions: [
        {
          name: '',
          imageUrl: '',
          subOptions: [''],
          tags: [''],
          review: '',
        },
      ],
      review: '',
      tags: [''],
      totalPrice: 0,
    },
  ],
};

const carInitialData = {
  archivingCars: [{ name: '', options: [''] }],
};

export function Archiving() {
  const {
    data: { archivings },
  } = useFetch<ArchivingDataProps>({
    defaultValue: initialData,
    url: '/archiving?model=1&options=1&options=2',
  });
  const {
    data: { archivingCars },
  } = useFetch<ArchivingCarDataProps>({
    defaultValue: carInitialData,
    url: '/archiving/cars',
  });

  const [selectedSearchOptions, setSelectedSearchOptions] = useState<Set<string>>(new Set());
  const [selectedCar, setSelectedCar] = useState('전체');

  const cars = ['전체', ...archivingCars.map(car => car.name)];
  const allOptions = [...new Set(archivingCars.flatMap(item => item.options))];

  const currentSelectedCar = archivingCars.find(({ name }) => name === selectedCar);
  const options = selectedCar === '전체' ? allOptions : currentSelectedCar!.options;

  function handleSelectOption(option: string) {
    setSelectedSearchOptions(prev => {
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
    <style.Container>
      <SearchBar selectedCar={selectedCar} onClick={handleSelectCar} cars={cars} />
      <OptionSearchBar options={options} onSelectOption={handleSelectOption} selectOptions={selectedSearchOptions} />
      <style.ReviewWrapper>
        {archivings.length === 0 ? (
          <style.InfoBox>조건에 맞는 결과가 없습니다.</style.InfoBox>
        ) : (
          <ReviewList archivings={archivings} options={selectedSearchOptions} onClick={handleSelectOption} />
        )}
      </style.ReviewWrapper>
    </style.Container>
  );
}
