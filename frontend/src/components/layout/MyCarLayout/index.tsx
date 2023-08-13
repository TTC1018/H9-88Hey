import { useEffect, useState } from 'react';

import { Outlet } from 'react-router-dom';

import { MyCarProps } from '@/types/trim';

import { Header } from '@/components/common/Header';
import { Footer } from '@/components/common/Footer';
import { Navigation } from '@/components/common/Navigation';

import * as Styled from './style';

const DEFAULT_STATE: MyCarProps = {
  model: { title: '', price: 0 },
  engine: { title: '', price: 0 },
  bodyType: { title: '', price: 0 },
  wheelDrive: { title: '', price: 0 },
  outerColor: { title: '', imageUrl: '', price: 0 },
  innerColor: { title: '', imageUrl: '', id: 1 },
  carImageUrl: '',
  options: [],
};

export function MyCarLayout() {
  const [trim, setTrim] = useState(DEFAULT_STATE);

  const totalPrice =
    trim.model.price + trim.engine.price + trim.bodyType.price + trim.wheelDrive.price + trim.outerColor.price;

  function handleTrim({ key, option, price }: { key: string; option: string; price: number }) {
    setTrim(prev => ({ ...prev, [key]: { title: option, price } }));
  }

  function handleOuterColor({ color, colorImage, price }: { color: string; colorImage: string; price: number }) {
    setTrim(prev => ({ ...prev, outerColor: { title: color, imageUrl: colorImage, price } }));
  }

  function handleInnerColor({ color, colorImage, id }: { color: string; colorImage: string; id: number }) {
    setTrim(prev => ({ ...prev, innerColor: { title: color, imageUrl: colorImage, id } }));
  }

  function handleCarImageUrl(carImageUrl: string) {
    setTrim(prev => ({ ...prev, carImageUrl }));
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
    <Styled.Container>
      <Header />
      <Navigation />
      <Styled.Wrapper>
        <Outlet
          context={{ handleTrim, handleOuterColor, handleCarImageUrl, handleInnerColor, addOption, removeOption, trim }}
        />
      </Styled.Wrapper>
      <Footer myCarData={trim} totalPrice={totalPrice} onSetLocalStorage={handleLocalStrage} />
    </Styled.Container>
  );
}
