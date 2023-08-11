import { useEffect, useState } from 'react';

import { Outlet, useLocation } from 'react-router-dom';

import { MyCarType } from '@/types/trim';

import { Header } from '@/components/common/Header';
import { Footer } from '@/components/common/Footer';
import { Navigation } from '@/components/common/Navigation';

import * as style from './style';

const DEFAULT_STATE: MyCarType = {
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
  const { pathname } = useLocation();

  const [trim, setTrim] = useState(DEFAULT_STATE);

  const trimKeys = ['model', 'engine', 'bodyType', 'wheelDrive'];

  const totalPrice =
    trimKeys.reduce((acc, cur) => acc + trim[cur].price, 0) + trim.options.reduce((acc, cur) => acc + cur.price, 0);

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
    <style.Container isFull={pathname === '/result'}>
      <Header />
      <Navigation />
      <style.Wrapper isFull={pathname === '/result'}>
        <Outlet context={{ handleTrim, trim, addOption, removeOption }} />
      </style.Wrapper>
      <Footer options={trim} totalPrice={totalPrice} onSetLocalStorage={handleLocalStrage} />
    </style.Container>
  );
}
