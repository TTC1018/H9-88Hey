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
    title: '2WD',
    price: 0,
    description:
      '엔진에서 전달되는 동력이 전/후륜 바퀴 중 한쪽으로만 전달되어 차량을 움직이는 방식입니다차체가 가벼워 연료 효율이 높습니다',
    image: 'https://www.hyundai.com/contents/spec/guide/lx_2wd_s.jpg',
  },
  {
    title: '4WD',
    price: 0,
    description:
      '전자식 상시 4륜 구동 시스템 입니다도로의 상황이나 주행 환경에 맞춰 전후륜 구동력을 자동배분하여 주행 안전성을 높여줍니다',
    image: 'https://www.hyundai.com/contents/spec/guide/lx_htrac_s.jpg',
  },
];

export function WheelDrive() {
  const [selectedIndex, handleSetIndex] = useSelectIndex();

  const {
    handleTrim,
    trim: { wheelDrive },
  } = useOutletContext<MyCarLayoutContextType>();
  const { title, price, image } = mockData[selectedIndex];

  function handleCardClick(index: number, price: number) {
    return () => {
      handleSetIndex(index)();
      handleTrim({ key: 'wheelDrive', option: mockData[index].title, price: price });
    };
  }

  useEffect(() => {
    const index = mockData.findIndex(card => card.title === wheelDrive.title);

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
          <MyCarImageBox hasOption={false} images={image} />
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
