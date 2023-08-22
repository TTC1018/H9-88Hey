import { Suspense, useReducer, useRef, useState } from 'react';

import { Outlet, useLocation } from 'react-router-dom';

import { getLocalStorage } from '@/utils';
import { ActionType, MyCarProps } from '@/types/trim';

import { Header } from '@/components/common/Header';
import { Footer } from '@/components/common/Footer';
import { Navigation } from '@/components/common/Navigation';
import { Loading } from '@/components/common/Loading';

import * as Styled from './style';

const initialState: MyCarProps = {
  carType: { krName: '펠리세이드', enName: 'Palisade' },
  trim: { name: '', price: 0, id: 0 },
  engine: { name: '', additionalPrice: 0, id: 0 },
  bodyType: { name: '', additionalPrice: 0, id: 0 },
  wheelDrive: { name: '', additionalPrice: 0, id: 0 },
  exteriorColor: { name: '', colorImageUrl: '', additionalPrice: 0 },
  interiorColor: { name: '', colorImageUrl: '', id: 0 },
  options: [],
  carImageUrl: '',
};

function reducer(state: MyCarProps, action: ActionType): MyCarProps {
  const { type, props } = action;

  switch (type) {
    case 'TRIM':
      return {
        ...state,
        trim: props,
      };
    case 'TRIM_OPTION':
      const { key, ...data } = props;
      return {
        ...state,
        [key]: data,
      };
    case 'EXTERIOR_COLOR':
      return {
        ...state,
        exteriorColor: props,
      };
    case 'INTERIOR_COLOR':
      return { ...state, interiorColor: props };
    case 'ADD_OPTION':
      return { ...state, options: [...state.options, props] };
    case 'REMOVE_OPTION':
      return { ...state, options: state.options.filter(({ name }) => name !== props) };
    case 'CAR_IMAGE_URL':
      return { ...state, carImageUrl: props };
    case 'SAVE_OPTION':
      return props;
    case 'CLEAR_OPTION':
      return { ...state, options: props };
    default:
      return state;
  }
}

export function MyCarLayout() {
  const { pathname } = useLocation();
  const carCodeData = getLocalStorage('carCode');

  const carCode = useRef(carCodeData === null ? '' : carCodeData);

  const localStorageData = JSON.parse(getLocalStorage('myCar'));
  const [myCar, dispatch] = useReducer(reducer, localStorageData === null ? initialState : localStorageData);

  const myCarKeysWithPrice = ['engine', 'bodyType', 'wheelDrive', 'exteriorColor'];

  const totalPrice =
    myCar.trim.price +
    myCarKeysWithPrice.reduce((acc, cur) => acc + myCar[cur].additionalPrice, 0) +
    myCar.options.reduce((acc, cur) => acc + cur.additionalPrice, 0);

  const isResultPage = pathname === '/result';

  return (
    <Styled.Container isFull={isResultPage}>
      <Header />
      <Navigation />
      <Styled.Wrapper isFull={isResultPage}>
        <Suspense fallback={<Loading />}>
          <Outlet
            context={{
              myCar,
              carCode,
              totalPrice,
              dispatch,
            }}
          />
        </Suspense>
      </Styled.Wrapper>
      <Footer myCarData={myCar} calculatePrice={totalPrice} carCode={carCode} dispatch={dispatch} />
    </Styled.Container>
  );
}
