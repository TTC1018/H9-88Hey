import { useEffect, useRef, useState } from 'react';

import { Outlet } from 'react-router-dom';

import { MyCarProps } from '@/types/trim';

import { useCountPrice } from '@/hooks/useCountPrice';
import { Header } from '@/components/common/Header';
import { Footer } from '@/components/common/Footer';
import { Navigation } from '@/components/common/Navigation';

import * as style from './style';

const initialState: MyCarProps = {
  model: { title: '', price: 0 },
  engine: { title: '', price: 0 },
  bodyType: { title: '', price: 0 },
  wheelDrive: { title: '', price: 0 },
  outerColor: { title: '', imageUrl: '', price: 0 },
  innerColor: { title: '', imageUrl: '', id: 1 },
  options: [],
};

export function MyCarLayout() {
  const [trim, setTrim] = useState(initialState);
  const calclPrice =
    trim.model.price + trim.engine.price + trim.bodyType.price + trim.wheelDrive.price + trim.outerColor.price;

  const prevPrice = useRef(calclPrice);

  const totalPrice = useCountPrice({
    prevPrice: prevPrice.current,
    nextPrice: calclPrice,
  });

  prevPrice.current = totalPrice;

  function handleTrim({ key, option, price }: { key: string; option: string; price: number }) {
    setTrim(prev => ({ ...prev, [key]: { title: option, price } }));
  }

  function handleOuterColor({ color, colorImage, price }: { color: string; colorImage: string; price: number }) {
    setTrim(prev => ({ ...prev, outerColor: { title: color, imageUrl: colorImage, price } }));
  }

  function handleInnerColor({ color, colorImage, id }: { color: string; colorImage: string; id: number }) {
    setTrim(prev => ({ ...prev, innerColor: { title: color, imageUrl: colorImage, id } }));
  }

  function addOption({ name, price }: { name: string; price: number }) {
    setTrim(prev => ({ ...prev, options: [...prev.options, { name, price }] }));
  }

  function removeOption(name: string) {
    setTrim(prev => ({ ...prev, options: prev.options.filter(options => options.name !== name) }));
  }

  function handleLocalStrage() {
    localStorage.setItem('carOptions', JSON.stringify(trim));
  }

  useEffect(() => {
    const localStorageData = localStorage.getItem('carOptions');

    if (localStorageData === null) {
      return;
    }

    const savedOptions: MyCarProps = JSON.parse(localStorageData);
    setTrim(savedOptions);
  }, []);

  return (
    <style.Container>
      <Header />
      <Navigation />
      <style.Wrapper>
        <Outlet context={{ handleTrim, handleOuterColor, handleInnerColor, addOption, removeOption, trim }} />
      </style.Wrapper>
      <Footer myCarData={trim} totalPrice={totalPrice} onSetLocalStorage={handleLocalStrage} />
    </style.Container>
  );
}
