import { useState, useEffect, MouseEvent } from 'react';

import {
  SelectOptionProps,
  SubOptionProps,
  SelectOptionCardDataProps,
  DefaultOptionCardDataProps,
} from '@/types/option';
import { OPTION_CARD_LIST_LENGTH } from '@/constants';

import { OptionImageBox } from '@/components/common/OptionImageBox';
import { OptionDescription } from '@/components/common/OptionDescription';
import { OptionDetailCard } from '@/components/common/OptionDetailCard';
import { OptionCategory } from '@/components/common/OptionCategory';
import { SelectOptionCardList } from '@/components/common/SelectOptionCardList';
import { DefaultOptionCardList } from '@/components/common/DefaultOptionCardList';

import * as style from './style';

const selectOptionMockData = [
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
        name: '헤드업 디스플레이',
        imageUrl: '/src/assets/4.jpeg',
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
        imageUrl: '/src/assets/4.jpeg',
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
        imageUrl: '/src/assets/4.jpeg',
        description:
          '초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다.',
      },
      {
        name: '헤드업 디스플레이',
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

const defaultOptionMockData = [
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

export function Option() {
  const [selectOption, setSelectOption] = useState<SelectOptionProps>({
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
  const [selectOptionCardData, setSelectOptionCardData] = useState<SelectOptionCardDataProps[]>([]);
  const [defaultOptionCardData, setDefaultOptionCardData] = useState<DefaultOptionCardDataProps[]>([]);

  const [menu, setMenu] = useState(0);

  const [selectOptionIndex, setSelectOptionIndex] = useState(0);
  const [subOptionIndex, setSubOptionIndex] = useState(0);

  const [selectOptionListIndex, setSelectOptionListIndex] = useState(0);
  const [defaultOptionListIndex, setDefaultOptionListIndex] = useState(0);

  // TODO: DefaultOptionCardList 컴포넌트로 분리
  const [categoryIndex, setCategoryIndex] = useState(0);
  const [categories, setCategories] = useState<string[]>([]);

  function handleChangeMenu(menu: number) {
    setMenu(menu);
  }

  function handleChangeOptionIndex(index: number, event: MouseEvent<HTMLDivElement>) {
    const target = event.target as HTMLDivElement;
    if (index === selectOptionIndex || target.tagName === 'BUTTON') {
      return;
    }
    setSelectOptionIndex(index);
    setSubOptionIndex(0);
  }

  function isValidIndex(index: number, maxIndex: number) {
    return index >= 0 && index < maxIndex;
  }

  function handleChangeSubOptionIndex(index: number) {
    const { length } = selectOption.subOptions;
    const newIndex = isValidIndex(index, length) ? index : (index + length) % length;
    setSubOptionIndex(newIndex);
  }

  function handleChangeCategoryIndex(index: number) {
    if (index === categoryIndex) {
      return;
    }
    setCategoryIndex(index);
    setDefaultOptionListIndex(0);
  }

  function handleChangeOptionListIndex(type: string, index: number, length: number) {
    if (!isValidIndex(index, Math.ceil(length / OPTION_CARD_LIST_LENGTH))) {
      return;
    }
    type === 'SELECT' ? setSelectOptionListIndex(index) : setDefaultOptionListIndex(index);
  }

  useEffect(() => {
    const { name, price, imageUrl, tags, subOptions } = selectOptionMockData[selectOptionIndex];
    const subOption = subOptions[subOptionIndex];
    const selectOptionCardData = selectOptionMockData.map(({ name, price, imageUrl, subOptions }, index) => ({
      index,
      name,
      price,
      imageUrl,
      subOptionNames: subOptions.map(({ name }) => name),
    }));

    setSelectOption({ name, price, imageUrl, tags, subOptions });
    setSubOption({
      name: subOption.name,
      imageUrl: subOption.imageUrl,
      description: subOption.description,
    });
    setSelectOptionCardData(selectOptionCardData);
  }, [selectOptionIndex, subOptionIndex]);

  // TODO: DefaultOptionCardList 컴포넌트로 분리
  useEffect(() => {
    const defaultOptionCardData = defaultOptionMockData[categoryIndex].subOptions.map(({ name, imageUrl }) => ({
      name,
      imageUrl,
    }));
    setDefaultOptionCardData(defaultOptionCardData);
  }, [categoryIndex]);

  // TODO: DefaultOptionCardList 컴포넌트로 분리
  useEffect(() => {
    setCategories(defaultOptionMockData.map(({ category }) => category));
  }, []);

  return (
    <style.Container>
      <style.OptionWrapper>
        <style.ImageBox>
          <OptionImageBox imageUrl={subOption.imageUrl} />
        </style.ImageBox>
        <style.DescriptionBox>
          <OptionDescription name={selectOption.name} price={selectOption.price} tags={selectOption.tags} />
          <OptionDetailCard
            index={subOptionIndex}
            length={selectOption.subOptions.length}
            name={subOption.name}
            description={subOption.description}
            onClick={handleChangeSubOptionIndex}
          />
        </style.DescriptionBox>
      </style.OptionWrapper>
      <style.CardWrapper>
        <OptionCategory menu={menu} onClick={handleChangeMenu} />
        <SelectOptionCardList
          isShow={menu === 0}
          selectedIndex={selectOptionIndex}
          cardListIndex={selectOptionListIndex}
          data={selectOptionCardData}
          onClickCard={handleChangeOptionIndex}
          onClickArrowButton={handleChangeOptionListIndex}
        />
        <DefaultOptionCardList
          isShow={menu === 1}
          categoryIndex={categoryIndex}
          cardListIndex={defaultOptionListIndex}
          categories={categories}
          data={defaultOptionCardData}
          onClickCategory={handleChangeCategoryIndex}
          onClickArrowButton={handleChangeOptionListIndex}
        />
      </style.CardWrapper>
    </style.Container>
  );
}
