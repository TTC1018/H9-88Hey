import { useState, useEffect } from 'react';

import { DefaultOptionCardDataProps } from '@/types/option';
import { isValidIndex, isIndexLargeThanZero, isIndexSmallThanMaxIndex } from '@/utils';
import { OPTION_CARD_LIST_LENGTH } from '@/constants';

import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';

import * as style from './style';

const mockData = [
  {
    category: '파워 트레인/성능',
    subOptions: [
      {
        name: 'ISG 시스템',
        imageUrl: '/src/assets/1.jpeg',
      },
      {
        name: '통합주행모드',
        imageUrl: '/src/assets/1.jpeg',
      },
      {
        name: 'ISG 시스템',
        imageUrl: '/src/assets/1.jpeg',
      },
      {
        name: '통합주행모드',
        imageUrl: '/src/assets/1.jpeg',
      },
      {
        name: 'ISG 시스템',
        imageUrl: '/src/assets/1.jpeg',
      },
      {
        name: '통합주행모드',
        imageUrl: '/src/assets/1.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/2.jpeg',
      },
    ],
  },
  {
    category: '지능형 안전기술',
    subOptions: [
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/2.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/2.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/2.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/2.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/2.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/2.jpeg',
      },
    ],
  },
  {
    category: '안전',
    subOptions: [
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/3.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/3.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/3.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/3.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/3.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/3.jpeg',
      },
    ],
  },
  {
    category: '외관',
    subOptions: [
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/4.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/4.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/4.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/4.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/4.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/4.jpeg',
      },
    ],
  },
  {
    category: '내장',
    subOptions: [
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/5.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/5.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/5.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/5.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/5.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/5.jpeg',
      },
    ],
  },
  {
    category: '시트',
    subOptions: [
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/6.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/6.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/6.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/6.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/6.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/6.jpeg',
      },
    ],
  },
  {
    category: '편의',
    subOptions: [
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/1.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/1.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/1.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/1.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/1.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/1.jpeg',
      },
    ],
  },
  {
    category: '멀티미디어',
    subOptions: [
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/2.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/2.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/2.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/2.jpeg',
      },
      {
        name: '하이빔 보조',
        imageUrl: '/src/assets/2.jpeg',
      },
      {
        name: '진동 경고 스티어링 휠',
        imageUrl: '/src/assets/2.jpeg',
      },
    ],
  },
];

interface Props {
  isShow: boolean;
}

export function DefaultOptionCardList({ isShow }: Props) {
  const [data, setData] = useState<DefaultOptionCardDataProps[]>([]);
  const [cardList, setCardList] = useState<DefaultOptionCardDataProps[]>([]);
  const [categories, setCategories] = useState<string[]>([]);

  const [categoryIndex, setCategoryIndex] = useState(0);
  const [cardListIndex, setCardListIndex] = useState(0);

  function handleChangeCategoryIndex(index: number) {
    if (index === categoryIndex) {
      return;
    }
    setCategoryIndex(index);
    setCardListIndex(0);
  }

  // TODO: 커스텀 훅으로 빼기
  function handleChangeCardListIndex(index: number, length: number) {
    if (!isValidIndex(index, Math.ceil(length / OPTION_CARD_LIST_LENGTH) - 1)) {
      return;
    }
    setCardListIndex(index);
  }

  // TODO: 커스텀 훅으로 빼기
  useEffect(() => {
    const data = mockData[categoryIndex].subOptions.map(({ name, imageUrl }) => ({
      name,
      imageUrl,
    }));

    const startIndex = cardListIndex * OPTION_CARD_LIST_LENGTH;
    let endIndex = startIndex + OPTION_CARD_LIST_LENGTH;
    if (endIndex > data.length) {
      endIndex = data.length;
    }

    setData(data);
    setCardList(data.slice(startIndex, endIndex));
  }, [categoryIndex, cardListIndex]);

  useEffect(() => {
    setCategories(mockData.map(({ category }) => category));
  }, []);

  return (
    <style.Container isShow={isShow}>
      <style.CategoryWrapper>
        {categories.map((category, index) => (
          <style.Category
            isActive={index === categoryIndex}
            onClick={() => handleChangeCategoryIndex(index)}
            key={category}
          >
            {category}
          </style.Category>
        ))}
      </style.CategoryWrapper>
      <style.OptionCardWrapper>
        <PrevButton
          width="48"
          height="48"
          onClick={() => handleChangeCardListIndex(cardListIndex - 1, data.length)}
          isShow={isIndexLargeThanZero(cardListIndex)}
        />
        {cardList.map(({ name, imageUrl }, index) => (
          <style.OptionCard key={index}>
            <style.Image src={imageUrl} />
            <style.TextWrapper>
              <style.Text>{name}</style.Text>
              <style.SubText>기본 포함</style.SubText>
            </style.TextWrapper>
          </style.OptionCard>
        ))}
        <NextButton
          width="48"
          height="48"
          onClick={() => handleChangeCardListIndex(cardListIndex + 1, data.length)}
          isShow={isIndexSmallThanMaxIndex(cardListIndex, data.length)}
        />
      </style.OptionCardWrapper>
    </style.Container>
  );
}
