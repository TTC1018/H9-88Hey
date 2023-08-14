import { Fragment, useState } from 'react';

import { useFetch } from '@/hooks/useFetch';
import { ArchivingCarDataProps, ArchivingDataProps, ArchivingProps } from '@/types/archiving';

import { OptionSearchBar } from '@/components/archiving/OptionSearchBar';
import { ReviewCard } from '@/components/archiving/ReviewCard';

import * as style from './style';
import { useNavigateWithData } from '@/hooks/useNavigateWithData';

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

  const { naviagteWithData } = useNavigateWithData({ path: '/archiving/detail' });

  const allOptionsSet = new Set<string>();
  archivingCars.forEach(item => {
    item.options.forEach(option => {
      allOptionsSet.add(option);
    });
  });
  const allOptions = Array.from(allOptionsSet);

  const currentSelectedCar = archivingCars.find(({ name }) => name === selectedCar);

  const options = selectedCar === '전체' ? allOptions : currentSelectedCar!.options;

  const selectedCars = archivings.filter(review => {
    if (selectedCar === '전체') {
      return true;
    }
    console.log(review.model, selectedCar);
    return review.model === selectedCar;
  });

  const selectedReviews = selectedCars.filter(review =>
    [...selectedSearchOptions].every(option =>
      review.selectedOptions.some(selectedOption => selectedOption.name === option)
    )
  );

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
      <style.CarSearchBar>
        <style.Car key={'전체'} isActive={'전체' === selectedCar} onClick={() => handleSelectCar('전체')}>
          {'전체'}
        </style.Car>
        {archivingCars.map(car => (
          <style.Car key={car.name} isActive={car.name === selectedCar} onClick={() => handleSelectCar(car.name)}>
            {car.name}
          </style.Car>
        ))}
      </style.CarSearchBar>
      <OptionSearchBar options={options} onSelectOption={handleSelectOption} selectOptions={selectedSearchOptions} />
      <style.ReviewWrapper>
        {selectedReviews.length === 0 ? (
          <style.InfoBox>조건에 맞는 결과가 없습니다.</style.InfoBox>
        ) : (
          <>
            <style.ReviewBox>
              {selectedReviews.map((review, idx) => {
                if (idx % 2 === 0)
                  return (
                    <style.Enclosure onClick={() => naviagteWithData({ state: review })}>
                      <ReviewCard
                        key={idx}
                        props={review}
                        isArchiving={true}
                        selectedSearchOptions={selectedSearchOptions}
                        onClick={handleSelectOption}
                      />
                    </style.Enclosure>
                  );
              })}
            </style.ReviewBox>
            <style.ReviewBox>
              {selectedReviews.map((review, idx) => {
                if (idx % 2 === 1)
                  return (
                    <style.Enclosure onClick={() => naviagteWithData({ state: review })}>
                      <ReviewCard
                        key={idx}
                        props={review}
                        isArchiving={true}
                        selectedSearchOptions={selectedSearchOptions}
                        onClick={handleSelectOption}
                      />
                    </style.Enclosure>
                  );
              })}
            </style.ReviewBox>
          </>
        )}
      </style.ReviewWrapper>
    </style.Container>
  );
}
