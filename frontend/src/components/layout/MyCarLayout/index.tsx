import { useEffect, useRef, useState } from 'react';

import { Outlet, useLocation } from 'react-router-dom';

import { MyCarProps } from '@/types/trim';
import { OptionContextProps } from '@/types/option';

import { useCountPrice } from '@/hooks/useCountPrice';
import { Header } from '@/components/common/Header';
import { Footer } from '@/components/common/Footer';
import { Navigation } from '@/components/common/Navigation';

import * as Styled from './style';

const DEFAULT_STATE: MyCarProps = {
  carType: { krName: '펠리세이드', enName: 'Palisade' },
  trim: { title: '', price: 0 },
  engine: { title: '', price: 0 },
  bodyType: { title: '', price: 0 },
  wheelDrive: { title: '', price: 0 },
  outerColor: { title: '', imageUrl: '/src/assets/icons/ellipse_123.png', price: 0 },
  innerColor: { title: '', imageUrl: '/src/assets/icons/ellipse_567.svg', id: 1 },
  options: [],
  carImageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/WC9/001.png', // 임시 mock data
};

export function MyCarLayout() {
  const { pathname } = useLocation();

  const [myCar, setMyCar] = useState(DEFAULT_STATE);

  const trimKeys = ['trim', 'engine', 'bodyType', 'wheelDrive', 'outerColor'];

  const calclPrice =
    trimKeys.reduce((acc, cur) => acc + myCar[cur].price, 0) + myCar.options.reduce((acc, cur) => acc + cur.price, 0);

  const prevPrice = useRef(calclPrice);

  const totalPrice = useCountPrice({
    prevPrice: prevPrice.current,
    nextPrice: calclPrice,
  });

  prevPrice.current = totalPrice;

  function handleTrim({ key, option, price }: { key: string; option: string; price: number }) {
    setMyCar(prev => ({ ...prev, [key]: { title: option, price } }));
  }

  function handleOuterColor({ color, colorImage, price }: { color: string; colorImage: string; price: number }) {
    setMyCar(prev => ({ ...prev, outerColor: { title: color, imageUrl: colorImage, price } }));
  }

  function handleInnerColor({ color, colorImage, id }: { color: string; colorImage: string; id: number }) {
    setMyCar(prev => ({ ...prev, innerColor: { title: color, imageUrl: colorImage, id } }));
  }

  function addOption({ name, price, imageUrl, subOptions }: OptionContextProps) {
    setMyCar(prev => ({ ...prev, options: [...prev.options, { name, price, imageUrl, subOptions }] }));
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
            totalPrice,
            handleTrim,
            handleOuterColor,
            handleInnerColor,
            handleCarImageUrl,
            addOption,
            removeOption,
          }}
        />
      </Styled.Wrapper>
      <Footer myCarData={myCar} totalPrice={totalPrice} onSetLocalStorage={handleLocalStorage} />
    </Styled.Container>
  );
}
