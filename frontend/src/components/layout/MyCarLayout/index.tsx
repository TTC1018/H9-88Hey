import { useEffect, useState } from 'react';

import { Outlet } from 'react-router-dom';

import { MyCarType } from '@/types/trim';

import { Header } from '@/components/common/Header';
import { Footer } from '@/components/common/Footer';
import { Navigation } from '@/components/common/Navigation';

import * as style from './style';

const initialState: MyCarType = {
  model: { title: '', price: 0 },
  engine: { title: '', price: 0 },
  bodyType: { title: '', price: 0 },
  wheelDrive: { title: '', price: 0 },
  color: {
    outer: ['', ''],
    inner: ['', ''],
  },
  options: [],
};

export function MyCarLayout() {
  const [trim, setTrim] = useState(initialState);

  const totalPrice = trim.model.price + trim.engine.price + trim.bodyType.price + trim.wheelDrive.price;

  function handleTrim({ key, option, price }: { key: string; option: string; price: number }) {
    setTrim(prev => ({ ...prev, [key]: { title: option, price: price } }));
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

    const savedOptions: MyCarType = JSON.parse(localStorageData);
    setTrim(savedOptions);
  }, []);

  return (
    <style.Container>
      <Header />
      <Navigation />
      <style.Wrapper>
        <Outlet context={{ handleTrim, trim, addOption, removeOption }} />
      </style.Wrapper>
      <Footer options={trim} totalPrice={totalPrice} onSetLocalStorage={handleLocalStrage} />
    </style.Container>
  );
}
