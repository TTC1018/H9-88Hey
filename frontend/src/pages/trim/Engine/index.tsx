import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { useSelectIndex } from '@/hooks/useSelectedIndex';
import { MyCarLayoutContextType } from '@/types/trim';

import { TrimCard } from '@/components/common/TrimCard';
import { MyCarImageBox } from '@/components/common/MyCarImageBox';
import { MyCarDescription } from '@/components/common/MyCarDescription';
import * as style from './style';

const mockData = [
  {
    title: '디젤 2.2',
    price: 14800000,
    description: '높은 토크로 파워풀한 드라이빙이 가능하며, 차급대비 연비 효율이 우수합니다.',
    power: '202/3,800PS/rpm',
    torque: '45.0/1,750~2,750kgf-m/rpm',
    image: 'https://www.hyundai.com/contents/spec/guide/lx_diesel2.2_s.jpg',
  },
  {
    title: '가솔린 3.8',
    price: 16200000,
    description:
      '고마력의 우수한 가속 성능을 확보하여, 넉넉하고 안정감 있는 주행이 가능합니다엔진의 진동이 적어 편안하고 조용한 드라이빙 감성을 제공합니다.',
    power: '295/6,000PS/rpm',
    torque: '36.2/5,200kgf-m/rpm',
    image: 'https://www.hyundai.com/contents/spec/guide/lx_gasoline3.8_s.jpg',
  },
];

export function Engine() {
  const [selectedIndex, handleSetIndex] = useSelectIndex();
  const { image, price, title } = mockData[selectedIndex];

  const {
    handleTrim,
    trim: { engine },
  } = useOutletContext<MyCarLayoutContextType>();

  function handleCardClick(index: number, price: number) {
    return () => {
      handleSetIndex(index)();
      handleTrim({ key: 'engine', option: mockData[index].title, price: price });
    };
  }

  useEffect(() => {
    const index = mockData.findIndex(card => card.title === engine.title);

    if (index !== -1) {
      handleSetIndex(index)();
      return;
    }

    handleCardClick(0, mockData[selectedIndex].price)();
  }, []);

  return (
    <style.Container>
      <style.Wrapper>
        <MyCarImageBox hasOption={false} images={image} />
        <MyCarDescription title={title} price={price} hasTag={false} />
      </style.Wrapper>
      <style.Wrapper>
        {mockData.map(({ title, price, description, power, torque }, index) => (
          <style.Box key={title} onClick={handleCardClick(index, price)}>
            <TrimCard
              isActive={index === selectedIndex}
              title={title}
              price={price}
              description={description}
              hasEngineInfo={true}
              power={power}
              torque={torque}
            />
          </style.Box>
        ))}
      </style.Wrapper>
    </style.Container>
  );
}
