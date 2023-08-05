import { useEffect, useState } from 'react';

import { useLocation } from 'react-router-dom';

import { NavArrowButton } from '@/components/common/NavArrowButton';

import * as style from './style';

const SELECTIONS = Object.freeze({
  trim: {
    title: '트림 선택',
    flow: {
      engine: '엔진',
      'body-type': '바디타입',
      'wheel-drive': '구동방식',
    },
  },
  color: {
    title: '색상 선택',
    flow: {
      '': '외장 색상 ▪︎ 내장 색상',
    },
  },
  option: {
    title: '옵션 선택',
    flow: {
      '': '선택 옵션',
      'h-genuine-accessories': 'H Genuine Accessories',
      'n-performance': 'N Performance',
    },
  },
});

type SelectionsType = 'trim' | 'color' | 'option';

export function Navigation() {
  const [orderNum, setOrderNum] = useState(1);
  const { pathname } = useLocation();
  const pathnameList = pathname.split('/').slice(1);

  const [titlePath, selectedPath] = pathnameList;

  const { flow, title } = SELECTIONS[titlePath as SelectionsType];

  useEffect(() => {
    const targetIndex = Object.keys(SELECTIONS).indexOf(titlePath) + 1;
    setOrderNum(targetIndex);
  }, [titlePath]);

  function isKeyEqualToSelectedPath(key: string) {
    return key === (selectedPath || '');
  }

  function isFlowLast(index: number) {
    return index === Object.entries(flow).length - 1;
  }

  function isOrderNumLessThanPage(page: number) {
    return orderNum < page;
  }

  return (
    <style.Container>
      <style.Wrapper>
        <style.Box>
          <style.OrderNumber isDisplay={true} isCurrent={true}>
            {orderNum}
          </style.OrderNumber>
          <style.OrderTitle>{title}</style.OrderTitle>
          <style.OrderBox>
            {Object.entries(flow).map(([key, value], index) => (
              <style.Order key={key}>
                <style.OrderText isCurrent={isKeyEqualToSelectedPath(key)}>{value}</style.OrderText>
                {!isFlowLast(index) && <NavArrowButton isCurrent={isKeyEqualToSelectedPath(key)} />}
              </style.Order>
            ))}
          </style.OrderBox>
        </style.Box>
        <style.Box>
          {[2, 3].map(page => (
            <style.OrderNumber key={page} isDisplay={isOrderNumLessThanPage(page)} isCurrent={false}>
              {page}
            </style.OrderNumber>
          ))}
        </style.Box>
      </style.Wrapper>
    </style.Container>
  );
}
