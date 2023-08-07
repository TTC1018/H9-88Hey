import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { MyCarLayoutContextType } from '@/types/trim';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { TrimCard } from '@/components/common/TrimCard';
import { MyCarImageBox } from '@/components/common/MyCarImageBox';
import { MyCarDescription } from '@/components/common/MyCarDescription';

import * as style from './style';

const mockData = [
  {
    title: '7인승',
    price: 0,
    description:
      '기존 8인승 시트(1열 2명, 2열 3명, 3열 3명)에서 2열 가운데 시트를 없애 2열 탑승객의 편의는 물론, 3열 탑승객의 승하차가 편리합니다.',
    images: [
      'https://www.hyundai.com/contents/spec/guide/lx_2wd_s.jpg',
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT1p89A1Ayz5SUzrKfHgl0hph8xUyYAqRlJ5A&usqp=CAU',
    ],
  },
  {
    title: '8인승',
    price: 0,
    description: '1열 2명, 2열 3명, 3열 3명이 탑승할 수 있는 구조로, 많은 인원이 탑승할 수 있도록 배려하였습니다',
    images: [
      'https://www.hyundai.com/contents/spec/guide/lx_htrac_s.jpg',
      'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUATgC4Xv06TncWaHMALgAiS0n4SoTG8iZ4g&usqp=CAU',
    ],
  },
];

export function BodyType() {
  const [selectedIndex, handleSetIndex] = useSelectIndex();
  const [selectedImageIndex, handleSetImageIndex] = useSelectIndex();

  const { images, title, price } = mockData[selectedIndex];

  const {
    handleTrim,
    trim: { bodyType },
  } = useOutletContext<MyCarLayoutContextType>();

  function handleCardClick(index: number, price: number) {
    return function () {
      handleSetIndex(index)();
      handleSetImageIndex(0)();
      handleTrim({ key: 'bodyType', option: mockData[index].title, price: price });
    };
  }

  useEffect(() => {
    const index = mockData.findIndex(card => card.title === bodyType.title);

    if (index !== -1) {
      handleSetIndex(index)();
      return;
    }

    handleCardClick(0, mockData[selectedIndex].price)();
  }, []);

  return (
    <style.Container>
      <style.Wrapper>
        <style.Box>
          <MyCarImageBox
            hasOption={true}
            images={images}
            selectedIndex={selectedImageIndex}
            onClick={handleSetImageIndex}
          />
          <MyCarDescription title={title} price={price} hasTag={false} />
        </style.Box>
        <style.Box>
          {mockData.map(({ title, price, description }, index) => (
            <style.Enclosure key={title} onClick={handleCardClick(index, price)}>
              <TrimCard
                isActive={index === selectedIndex}
                title={title}
                price={price}
                description={description}
                hasEngineInfo={false}
              />
            </style.Enclosure>
          ))}
        </style.Box>
      </style.Wrapper>
    </style.Container>
  );
}
