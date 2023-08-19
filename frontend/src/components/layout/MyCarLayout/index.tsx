import { Suspense, useReducer, useRef } from 'react';

import { Outlet, useLocation } from 'react-router-dom';

import { getLocalStorage } from '@/utils';

import { ActionType, MyCarProps } from '@/types/trim';

import { Header } from '@/components/common/Header';
import { Footer } from '@/components/common/Footer';
import { Loading } from '@/components/common/Loading';
import { Navigation } from '@/components/common/Navigation';

import * as Styled from './style';

const initialState: MyCarProps = {
  carType: { krName: '펠리세이드', enName: 'Palisade' },
  trim: { name: '', price: 0, id: 0 },
  engine: { name: '', additionalPrice: 0, id: 0 },
  bodyType: { name: '', additionalPrice: 0, id: 0 },
  wheelDrive: { name: '', additionalPrice: 0, id: 0 },
  exteriorColor: { name: '', colorImageUrl: '/src/assets/icons/ellipse_123.png', additionalPrice: 0 },
  interiorColor: { name: '', colorImageUrl: '/src/assets/icons/ellipse_567.svg', id: 1 },
  options: [],
  carImageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/WC9/001.png', // 임시 mock data
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
    default:
      return state;
  }
}

export function MyCarLayout() {
  const { pathname } = useLocation();
  const carCode = useRef('');

  const localStorageData = getLocalStorage('myCar');
  const [myCar, dispatch] = useReducer(reducer, localStorageData === null ? initialState : localStorageData);

  const myCarKeysWithPrice = ['engine', 'bodyType', 'wheelDrive', 'exteriorColor'];

  const totalPrice =
    myCar.trim.price +
    myCarKeysWithPrice.reduce((acc, cur) => acc + myCar[cur].additionalPrice, 0) +
    myCar.options.reduce((acc, cur) => acc + cur.additionalPrice, 0);

  function handleLocalStorage() {
    localStorage.setItem('myCar', JSON.stringify(myCar));
  }

  // useEffect(() => {
  //   const savedOptions: MyCarProps = JSON.parse(localStorageData);

  //   dispatch({ type: MyCarActionTypeProps.SAVE_OPTION, props: savedOptions });
  // }, []);

  return (
    <Styled.Container isFull={pathname === '/result'}>
      <Header />
      <Navigation />
      <Styled.Wrapper isFull={pathname === '/result'}>
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
      <Footer myCarData={myCar} calculatePrice={totalPrice} onSetLocalStorage={handleLocalStorage} carCode={carCode} />
    </Styled.Container>
  );
}
