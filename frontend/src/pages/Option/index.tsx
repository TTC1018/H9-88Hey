import { useState, useEffect, MouseEvent } from 'react';

import { OptionProps, SubOptionProps, OptionCardDataProps } from '@/types/option';
import { isValidIndex } from '@/utils';
import { OPTION_CARD_LIST_LENGTH } from '@/constants';

import { OptionImageBox } from '@/components/common/OptionImageBox';
import { OptionDescription } from '@/components/common/OptionDescription';
import { OptionDetailCard } from '@/components/common/OptionDetailCard';
import { OptionCategory } from '@/components/common/OptionCategory';
import { OptionCardList } from '@/components/common/OptionCardList';
import { DefaultOptionCardList } from '@/components/common/DefaultOptionCardList';

import * as style from './style';

const mockData = [
  {
    name: '컴포트 2',
    price: 1090000,
    imageUrl: '/src/assets/1.jpeg',
    tags: ['여름에 쓰기 좋아요☀️', '옵션값 뽑았어요👍', '편리해요☺️'],
    subOptions: [
      {
        name: '후석 승객 알림',
        imageUrl: '/src/assets/1.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '메탈 리어범퍼스텝',
        imageUrl: '/src/assets/leblanc.jpeg',
        description:
          '러기지 룸 앞쪽 하단부를 메탈로 만들어 물건을 싣고 내릴 때나 사람이 올라갈 때 차체를 보호해줍니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/3.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/4.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/5.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/6.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
    ],
  },
  {
    name: '현대스마트센스 Ⅰ',
    price: 1090000,
    imageUrl: '/src/assets/2.jpeg',
    tags: ['여름에 쓰기 좋아요☀️', '옵션값 뽑았어요👍', '편리해요☺️'],
    subOptions: [
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/2.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/2.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/2.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/2.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/2.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/2.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
    ],
  },
  {
    name: '2열 통풍 시트',
    price: 1090000,
    imageUrl: '/src/assets/3.jpeg',
    tags: ['여름에 쓰기 좋아요☀️', '옵션값 뽑았어요👍', '편리해요☺️'],
    subOptions: [
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/3.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/3.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/3.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/3.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/3.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/3.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
    ],
  },
  {
    name: '듀얼 와이드 선루프',
    price: 1090000,
    imageUrl: '/src/assets/4.jpeg',
    tags: ['여름에 쓰기 좋아요☀️', '옵션값 뽑았어요👍', '편리해요☺️'],
    subOptions: [
      {
        name: '듀얼 와이드 선루프',
        imageUrl: '/src/assets/4.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
    ],
  },
  {
    name: '빌트인 캠(보조배터리 전용)',
    price: 1090000,
    imageUrl: '/src/assets/5.jpeg',
    tags: ['여름에 쓰기 좋아요☀️', '옵션값 뽑았어요👍', '편리해요☺️'],
    subOptions: [
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/5.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/5.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/5.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/5.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/5.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/5.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
    ],
  },
  {
    name: '주차보조 시스템 Ⅱ',
    price: 1090000,
    imageUrl: '/src/assets/6.jpeg',
    tags: ['여름에 쓰기 좋아요☀️', '옵션값 뽑았어요👍', '편리해요☺️'],
    subOptions: [
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/6.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/6.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/6.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/6.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/6.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/6.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
    ],
  },
  {
    name: '컴포트 2',
    price: 1090000,
    imageUrl: '/src/assets/1.jpeg',
    tags: ['여름에 쓰기 좋아요☀️', '옵션값 뽑았어요👍', '편리해요☺️'],
    subOptions: [
      {
        name: '후석 승객 알림',
        imageUrl: '/src/assets/1.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '메탈 리어범퍼스텝',
        imageUrl: '/src/assets/leblanc.jpeg',
        description:
          '러기지 룸 앞쪽 하단부를 메탈로 만들어 물건을 싣고 내릴 때나 사람이 올라갈 때 차체를 보호해줍니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/3.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/4.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/5.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/6.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
    ],
  },
];

export function Option() {
  const [option, setOption] = useState<OptionProps>({
    name: '',
    price: 0,
    imageUrl: '',
    tags: [],
    subOptions: [],
  });
  const [subOption, setSubOption] = useState<SubOptionProps>({
    name: '',
    imageUrl: '',
    description: '',
  });
  const [cardListData, setCardListData] = useState<OptionCardDataProps[]>([]);

  const [menu, setMenu] = useState(0);
  const [cardListIndex, setCardListIndex] = useState(0);
  const [optionIndex, setOptionIndex] = useState(0);
  const [subOptionIndex, setSubOptionIndex] = useState(0);

  function handleChangeMenu(menu: number) {
    setMenu(menu);
  }

  function handleChangeOptionIndex(index: number, event: MouseEvent<HTMLDivElement>) {
    const target = event.target as HTMLDivElement;
    if (index === optionIndex || target.tagName === 'BUTTON') {
      return;
    }
    setOptionIndex(index);
    setSubOptionIndex(0);
  }

  function handleChangeSubOptionIndex(index: number) {
    const { length } = option.subOptions;
    const newIndex = isValidIndex(index, length - 1) ? index : (index + length) % length;
    setSubOptionIndex(newIndex);
  }

  function handleChangeCardListIndex(index: number, length: number) {
    if (!isValidIndex(index, Math.ceil(length / OPTION_CARD_LIST_LENGTH) - 1)) {
      return;
    }
    setCardListIndex(index);
  }

  useEffect(() => {
    const { name, price, imageUrl, tags, subOptions } = mockData[optionIndex];
    const subOption = subOptions[subOptionIndex];
    const cardListData = mockData.map(({ name, price, imageUrl, subOptions }, index) => ({
      index,
      name,
      price,
      imageUrl,
      subOptionNames: subOptions.map(({ name }) => name),
    }));

    setOption({ name, price, imageUrl, tags, subOptions });
    setSubOption({
      name: subOption.name,
      imageUrl: subOption.imageUrl,
      description: subOption.description,
    });
    setCardListData(cardListData);
  }, [optionIndex, subOptionIndex]);

  return (
    <style.Container>
      <style.OptionWrapper>
        <style.ImageBox>
          <OptionImageBox imageUrl={subOption.imageUrl} />
        </style.ImageBox>
        <style.DescriptionBox>
          <OptionDescription name={option.name} price={option.price} tags={option.tags} />
          <OptionDetailCard
            index={subOptionIndex}
            length={option.subOptions.length}
            name={subOption.name}
            description={subOption.description}
            onClick={handleChangeSubOptionIndex}
          />
        </style.DescriptionBox>
      </style.OptionWrapper>
      <style.CardWrapper>
        <OptionCategory menu={menu} onClick={handleChangeMenu} />
        <OptionCardList
          isShow={menu === 0}
          selectedIndex={optionIndex}
          cardListIndex={cardListIndex}
          data={cardListData}
          onClickCard={handleChangeOptionIndex}
          onClickArrowButton={handleChangeCardListIndex}
        />
        <DefaultOptionCardList isShow={menu === 1} />
      </style.CardWrapper>
    </style.Container>
  );
}
