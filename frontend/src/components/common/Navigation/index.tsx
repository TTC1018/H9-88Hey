import { useEffect, useState } from 'react';

import { useLocation } from 'react-router-dom';

import { NavArrowButton } from '@/components/common/NavArrowButton';

import * as Styled from './style';

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
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Box>
          <Styled.OrderNumber isDisplay={true} isCurrent={true}>
            {orderNum}
          </Styled.OrderNumber>
          <Styled.OrderTitle>{title}</Styled.OrderTitle>
          <Styled.OrderBox>
            {Object.entries(flow).map(([key, value], index) => (
              <Styled.Order key={key}>
                <Styled.OrderText isCurrent={isKeyEqualToSelectedPath(key)}>{value}</Styled.OrderText>
                {!isFlowLast(index) && <NavArrowButton isCurrent={isKeyEqualToSelectedPath(key)} />}
              </Styled.Order>
            ))}
          </Styled.OrderBox>
        </Styled.Box>
        <Styled.Box>
          {[2, 3].map(page => (
            <Styled.OrderNumber key={page} isDisplay={isOrderNumLessThanPage(page)} isCurrent={false}>
              {page}
            </Styled.OrderNumber>
          ))}
        </Styled.Box>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
