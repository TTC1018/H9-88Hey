import { OptionSearchBar } from '@/components/archiving/OptionSearchBar';
import { ReviewCard } from '@/components/archiving/ReviewCard';
import { useState } from 'react';
import * as style from './style';

const options = [
  '컴포트 || 패키지',
  '주차 보조 시스템 ||',
  '2열 통풍시트',
  '듀얼 와이드 선루프',
  '현대스마트센스 |',
  '빌트인 캠(보조배터리 포함)',
  '듀얼 머플러 패키지',
  '사이드스텝',
  '빌트인 공기청정기',
  '적외선 무릎워머',
  '차량 보호 필름',
];
const reviews = [
  {
    model: '펠리세이드 Le Blanc',
    trim: '디젤 2.2 / 4WD / 7인승',
    outerColor: '문라이트 블루펄',
    innerColor: '퀄팅 천연(블랙)',
    options: ['컴포트 || 패키지', '듀얼 와이드 선루프', '사이드 스텝', '차량 보호 필름'],
    date: '23년 7월 19일에 시승했어요',
    description:
      ' 승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.',
    tags: ['편리해요😉', '이것만 있으면 나도 주차고수🚘', '대형견도 문제 없어요🐶'],
  },
  {
    model: '펠리세이드 Le Blanc',
    trim: '디젤 2.2 / 4WD / 7인승',
    outerColor: '문라이트 블루펄',
    innerColor: '퀄팅 천연(블랙)',
    options: ['컴포트 || 패키지', '듀얼 와이드 선루프'],
    date: '23년 7월 19일에 시승했어요',
    description:
      ' 승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.',
    tags: ['편리해요😉', '이것만 있으면 나도 주차고수🚘', '대형견도 문제 없어요🐶'],
  },
  {
    model: '펠리세이드 Le Blanc',
    trim: '디젤 2.2 / 4WD / 7인승',
    outerColor: '문라이트 블루펄',
    innerColor: '퀄팅 천연(블랙)',
    options: ['컴포트 || 패키지', '듀얼 와이드 선루프'],
    date: '23년 7월 19일에 시승했어요',
    description:
      ' 승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.',
    tags: ['편리해요😉', '이것만 있으면 나도 주차고수🚘', '대형견도 문제 없어요🐶'],
  },
  {
    model: '펠리세이드 Le Blanc',
    trim: '디젤 2.2 / 4WD / 7인승',
    outerColor: '문라이트 블루펄',
    innerColor: '퀄팅 천연(블랙)',
    options: ['컴포트 || 패키지', '듀얼 와이드 선루프'],
    date: '23년 7월 19일에 시승했어요',
    description:
      ' 승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.',
    tags: ['편리해요😉', '이것만 있으면 나도 주차고수🚘', '대형견도 문제 없어요🐶'],
  },
  {
    model: '펠리세이드 Le Blanc',
    trim: '디젤 2.2 / 4WD / 7인승',
    outerColor: '문라이트 블루펄',
    innerColor: '퀄팅 천연(블랙)',
    options: ['컴포트 || 패키지', '듀얼 와이드 선루프'],
    date: '23년 7월 19일에 시승했어요',
    description:
      ' 승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할 것 같습니다. 다른 주차 관련 옵션도 괜찮아요.',
    tags: ['편리해요😉', '이것만 있으면 나도 주차고수🚘', '대형견도 문제 없어요🐶'],
  },
];
const cars = ['전체', '펠리세이드', '베뉴', '코나', '싼타페', '그랜저', '아반뗴', '아이오닉'];

export function Archiving() {
  const [selectOptions, setSelectOptions] = useState<Set<string>>(new Set());
  const [selectCar, setSelectCar] = useState('전체');

  const selectedCars = reviews.filter(review => {
    if (selectCar === '전체') return true;
    return review.model.includes(selectCar);
  });
  const selectedReviews = selectedCars.filter(review =>
    [...selectOptions].every(option => review.options.includes(option))
  );

  function handleSelectOption(option: string) {
    setSelectOptions(prev => {
      if (prev.has(option)) {
        const newSet = new Set(prev);
        newSet.delete(option);
        return newSet;
      } else {
        return new Set([...prev, option]);
      }
    });
  }
  function handleSelectCar(car: string) {
    setSelectCar(car);
  }

  return (
    <style.Container>
      <style.CarSearchBar>
        {cars.map(car => (
          <style.Car isActive={car === selectCar} onClick={() => handleSelectCar(car)}>
            {car}
          </style.Car>
        ))}
      </style.CarSearchBar>
      <OptionSearchBar options={options} onSelectOption={handleSelectOption} selectOptions={selectOptions} />
      <style.ReviewWrapper>
        {selectedReviews.length === 0 ? (
          <style.InfoBox>조건에 맞는 결과가 없습니다.</style.InfoBox>
        ) : (
          selectedReviews.map(({ model, trim, outerColor, innerColor, options, date, description, tags }) => (
            <ReviewCard
              model={model}
              trim={trim}
              outerColor={outerColor}
              innerColor={innerColor}
              options={options}
              date={date}
              description={description}
              tags={tags}
              isArchiving={true}
              selectedOptions={selectOptions}
            />
          ))
        )}
      </style.ReviewWrapper>
    </style.Container>
  );
}
