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
  const { type, payload } = action;

  switch (type) {
    case 'TRIM':
      return {
        ...state,
        trim: payload,
      };
    case 'TRIM_OPTION':
      const { key, ...data } = payload;
      return {
        ...state,
        [key]: data,
      };
    case 'EXTERIOR_COLOR':
      return {
        ...state,
        exteriorColor: payload,
        carImageUrl: payload.carImagePath,
      };
    case 'INTERIOR_COLOR':
      return { ...state, interiorColor: payload };
    case 'ADD_OPTION':
      return { ...state, options: [...state.options, payload] };
    case 'REMOVE_OPTION':
      return { ...state, options: state.options.filter(({ name }) => name !== payload) };
    case 'SAVE_OPTION':
      return payload;
    case 'CLEAR_OPTION':
      return { ...state, options: payload };
    case 'CLEAR_COLORS':
      return { ...state, exteriorColor: initialState.exteriorColor, interiorColor: initialState.interiorColor };
    default:
      return state;
  }
}

export function MyCarLayout() {
  const { pathname } = useLocation();
  const carCodeData = getLocalStorage('carCode');

  const [isSavingNow, setIsSavingNow] = useState(false);
  const carCode = useRef(carCodeData === null ? '' : carCodeData);

  const localStorageData = JSON.parse(getLocalStorage('myCar'));
  const [myCar, dispatch] = useReducer(reducer, localStorageData === null ? initialState : localStorageData);

  const myCarKeysWithPrice = ['engine', 'bodyType', 'wheelDrive', 'exteriorColor'];

  const totalPrice =
    myCar.trim.price +
    myCarKeysWithPrice.reduce((acc, cur) => acc + myCar[cur].additionalPrice, 0) +
    myCar.options.reduce((acc, cur) => acc + cur.additionalPrice, 0);

  const isResultPage = pathname === '/result';

  if (isSavingNow) {
    setTimeout(() => {
      setIsSavingNow(false);
    }, 2000);
  }

  function setAutoSaving() {
    setIsSavingNow(true);
  }

  return (
    <Styled.Container isFull={isResultPage}>
      <Header isSaving={isSavingNow} />
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
      <Footer
        myCarData={myCar}
        calculatePrice={totalPrice}
        setDisplayAutoSaving={setAutoSaving}
        carCode={carCode}
        dispatch={dispatch}
      />
    </Styled.Container>
  );
}
