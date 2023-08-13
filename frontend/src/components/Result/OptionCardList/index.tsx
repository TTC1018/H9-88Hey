import { OptionCard } from '@/components/Result/OptionCard';

import * as Styled from './style';

const mockData = [
  {
    imageUrl: 'https://www.hyundai.com/contents/spec/LX24/roa.jpg',
    name: '컴포트  ||',
    price: 1090000,
    subOptions: [
      '후석 승객 알림',
      '메탈 리어범퍼스텝',
      '메탈 도어스커프',
      '3열 파워폴딩시트',
      '3열 열선시트',
      '헤드업 디스플레이',
    ],
  },
  {
    imageUrl: 'https://www.hyundai.com/contents/spec/LX24/roa.jpg',
    name: '컴포트  ||',
    price: 1090000,
    subOptions: [
      '후석 승객 알림',
      '메탈 리어범퍼스텝',
      '메탈 도어스커프',
      '3열 파워폴딩시트',
      '3열 열선시트',
      '헤드업 디스플레이',
    ],
  },
  {
    imageUrl: 'https://www.hyundai.com/contents/spec/LX24/roa.jpg',
    name: '컴포트  ||',
    price: 1090000,
    subOptions: [
      '후석 승객 알림',
      '메탈 리어범퍼스텝',
      '메탈 도어스커프',
      '3열 파워폴딩시트',
      '3열 열선시트',
      '헤드업 디스플레이',
    ],
  },
  {
    imageUrl: 'https://www.hyundai.com/contents/spec/LX24/roa.jpg',
    name: '컴포트  ||',
    price: 1090000,
    subOptions: [
      '후석 승객 알림',
      '메탈 리어범퍼스텝',
      '메탈 도어스커프',
      '3열 파워폴딩시트',
      '3열 열선시트',
      '헤드업 디스플레이',
    ],
  },
  {
    imageUrl: 'https://www.hyundai.com/contents/spec/LX24/roa.jpg',
    name: '컴포트  ||',
    price: 1090000,
    subOptions: [
      '후석 승객 알림',
      '메탈 리어범퍼스텝',
      '메탈 도어스커프',
      '3열 파워폴딩시트',
      '3열 열선시트',
      '헤드업 디스플레이',
    ],
  },
  {
    imageUrl: 'https://www.hyundai.com/contents/spec/LX24/roa.jpg',
    name: '컴포트  ||',
    price: 1090000,
    subOptions: [
      '후석 승객 알림',
      '메탈 리어범퍼스텝',
      '메탈 도어스커프',
      '3열 파워폴딩시트',
      '3열 열선시트',
      '헤드업 디스플레이',
    ],
  },
];

export function OptionCardList() {
  return (
    <>
      <Styled.OptionTitleWrapper>
        <Styled.OptionTitleBox>
          <Styled.OptionTitle>선택 옵션</Styled.OptionTitle>
          <Styled.OptionCount>6</Styled.OptionCount>
          <Styled.OptionUnit>개</Styled.OptionUnit>
        </Styled.OptionTitleBox>
      </Styled.OptionTitleWrapper>
      <Styled.OptionCardWrapper>
        <Styled.OptionCardBox>
          {mockData.map(({ imageUrl, name, price, subOptions }, index) => (
            <OptionCard
              imageUrl={imageUrl}
              name={name}
              price={price}
              subOptions={subOptions}
              index={index}
              key={name}
            />
          ))}
        </Styled.OptionCardBox>
      </Styled.OptionCardWrapper>
    </>
  );
}
