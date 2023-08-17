import { useEffect, useRef, useState } from 'react';

import { Outlet, useLocation } from 'react-router-dom';

import { getLocalStorage } from '@/utils';
import {
  ExteriorColorDataProps,
  HandleTrimOptionProps,
  InteriorColorDataProps,
  MyCarProps,
  TrimBaseProps,
} from '@/types/trim';
import { OptionContextProps } from '@/types/option';

import { useCountPrice } from '@/hooks/useCountPrice';
import { Header } from '@/components/common/Header';
import { Footer } from '@/components/common/Footer';
import { Navigation } from '@/components/common/Navigation';

import * as Styled from './style';

const DEFAULT_STATE: MyCarProps = {
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
export function MyCarLayout() {
  const { pathname } = useLocation();
  const carCode = useRef('');

  const localStorageData = getLocalStorage('myCar');

  const [myCar, setMyCar] = useState<MyCarProps>(localStorageData === null ? DEFAULT_STATE : localStorageData);

  const myCarKeysWithPrice = ['engine', 'bodyType', 'wheelDrive', 'exteriorColor'];

  const calclPrice =
    myCar.trim.price +
    myCarKeysWithPrice.reduce((acc, cur) => acc + myCar[cur].additionalPrice, 0) +
    myCar.options.reduce((acc, cur) => acc + cur.price, 0);

  const prevPrice = useRef(calclPrice);

  const totalPrice = useCountPrice({
    prevPrice: prevPrice.current,
    nextPrice: calclPrice,
  });

  prevPrice.current = totalPrice;

  function handleTrim(props: TrimBaseProps) {
    setMyCar(prev => ({ ...prev, trim: props }));
  }

  function handleTrimOption({ key, ...props }: HandleTrimOptionProps) {
    setMyCar(prev => ({ ...prev, [key]: props }));
  }

  function handleExteriorColor(props: ExteriorColorDataProps) {
    setMyCar(prev => ({ ...prev, exteriorColor: props }));
  }

  function handleInteriorColor(props: InteriorColorDataProps) {
    setMyCar(prev => ({ ...prev, interiorColor: props }));
  }

  function addOption(props: OptionContextProps) {
    setMyCar(prev => ({ ...prev, options: [...prev.options, props] }));
  }

  function handleCarImageUrl(carImageUrl: string) {
    setMyCar(prev => ({ ...prev, carImageUrl }));
  }

  function removeOption(name: string) {
    setMyCar(prev => ({ ...prev, options: prev.options.filter(options => options.name !== name) }));
  }

  function handleLocalStorage() {
    localStorage.setItem('myCar', JSON.stringify(myCar));
  }

  useEffect(() => {
    const localStorageData = localStorage.getItem('myCar');

    if (localStorageData === null) {
      return;
    }
    const savedOptions: MyCarProps = JSON.parse(localStorageData);
    setMyCar(savedOptions);
  }, []);

  return (
    <Styled.Container isFull={pathname === '/result'}>
      <Header />
      <Navigation />
      <Styled.Wrapper isFull={pathname === '/result'}>
        <Outlet
          context={{
            myCar,
            carCode,
            totalPrice,
            handleTrim,
            handleTrimOption,
            handleExteriorColor,
            handleInteriorColor,
            handleCarImageUrl,
            addOption,
            removeOption,
          }}
        />
      </Styled.Wrapper>
      <Footer myCarData={myCar} totalPrice={totalPrice} onSetLocalStorage={handleLocalStorage} carCode={carCode} />
    </Styled.Container>
  );
}
