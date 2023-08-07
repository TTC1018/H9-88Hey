import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { MyCarLayoutContextType } from '@/types/trim';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { MyCarImageBox } from '@/components/common/MyCarImageBox';
import { SelectOptionCard } from '@/components/trim/SelectOptionCard';

import * as style from './style';

const mockData = [
  {
    title: 'Le Blanc(르블랑)',
    price: 47720000,
    carImages: [
      'https://www.hyundai.com/contents/vr360/LX06/exterior/A2B/001.png',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F8c343c%2F574691FAF8211C9B0062C36B47DAB069D716EF12D7C7683B36_3JA1&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F54f09c%2F5F68383D23E12EC9239B7385B37186C4B250A0F83043A92FA6_36OF&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Ffa4cc2%2F4AD940F9C9E82ABEFF650A58DBDD430D3AA22E10CBA3BCD93D_3QMO&scode=media',
    ],
    optionImages: [
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-001.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DB-USP-002.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-002.png',
    ],
  },
  {
    title: 'Exclusive',
    price: 51720000,
    carImages: [
      'https://www.hyundai.com/contents/vr360/LX06/exterior/R2T/001.png',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F8c343c%2F574691FAF8211C9B0062C36B47DAB069D716EF12D7C7683B36_3JA1&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F54f09c%2F5F68383D23E12EC9239B7385B37186C4B250A0F83043A92FA6_36OF&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Ffa4cc2%2F4AD940F9C9E82ABEFF650A58DBDD430D3AA22E10CBA3BCD93D_3QMO&scode=media',
    ],
    optionImages: [
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-001.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DB-USP-002.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-002.png',
    ],
  },
  {
    title: 'Prestige',
    price: 107720000,
    carImages: [
      'https://www.hyundai.com/contents/vr360/LX06/exterior/D2S/001.png',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F8c343c%2F574691FAF8211C9B0062C36B47DAB069D716EF12D7C7683B36_3JA1&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F54f09c%2F5F68383D23E12EC9239B7385B37186C4B250A0F83043A92FA6_36OF&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Ffa4cc2%2F4AD940F9C9E82ABEFF650A58DBDD430D3AA22E10CBA3BCD93D_3QMO&scode=media',
    ],
    optionImages: [
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-001.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DB-USP-002.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-002.png',
    ],
  },
  {
    title: 'Calligraphy',
    price: 42120000,
    carImages: [
      'https://www.hyundai.com/contents/vr360/LX06/exterior/WC9/001.png',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F8c343c%2F574691FAF8211C9B0062C36B47DAB069D716EF12D7C7683B36_3JA1&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2F54f09c%2F5F68383D23E12EC9239B7385B37186C4B250A0F83043A92FA6_36OF&scode=media',
      'https://img1.daumcdn.net/thumb/S1100x620ht.u/?fname=%2Fmedia%2Fvitraya%2Fauto%2Fimage%2Ffa4cc2%2F4AD940F9C9E82ABEFF650A58DBDD430D3AA22E10CBA3BCD93D_3QMO&scode=media',
    ],
    optionImages: [
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-001.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DB-USP-002.png',
      'https://www.hyundai.com/contents/vr360/LX06/trim/DS-USP-002.png',
    ],
  },
];

export function Trim() {
  const [selectedIndex, handleSetIndex] = useSelectIndex();
  const [selectedImageIndex, handleSetImageIndex] = useSelectIndex();

  const { carImages } = mockData[selectedIndex];

  const {
    handleTrim,
    trim: { model },
  } = useOutletContext<MyCarLayoutContextType>();

  function handleCardClick(index: number, price: number) {
    return () => {
      handleSetIndex(index)();
      handleTrim({ key: 'model', option: mockData[index].title, price: price });
      handleSetImageIndex(0)();
    };
  }

  useEffect(() => {
    const index = mockData.findIndex(card => card.title === model.title);

    index !== -1 && handleSetIndex(index)();
  }, [model.title]);

  return (
    <style.Container>
      <MyCarImageBox
        hasOption={true}
        images={carImages}
        selectedIndex={selectedImageIndex}
        onClick={handleSetImageIndex}
      />
      <style.Wrapper>
        {mockData.map(({ title, price, optionImages }, index) => (
          <style.Box key={title} onClick={handleCardClick(index, price)}>
            <SelectOptionCard isActive={index === selectedIndex} title={title} price={price} images={optionImages} />
          </style.Box>
        ))}
      </style.Wrapper>
    </style.Container>
  );
}
