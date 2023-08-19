import { Suspense, useEffect, useRef, useState } from 'react';

import { Outlet, useLocation } from 'react-router-dom';

import { checkIsResultPage, getLocalStorage } from '@/utils';
import { MyCarProps } from '@/types/trim';
import { OptionContextProps } from '@/types/option';

import { useCountPrice } from '@/hooks/useCountPrice';
import { Header } from '@/components/common/Header';
import { Footer } from '@/components/common/Footer';
import { Navigation } from '@/components/common/Navigation';
import { Loading } from '@/components/common/Loading';

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

  const myCarData = JSON.parse(getLocalStorage('myCar'));
  const carCodeData = getLocalStorage('carCode');

  const [myCar, setMyCar] = useState<MyCarProps>(myCarData === null ? DEFAULT_STATE : myCarData);

  const carCode = useRef('');
  carCode.current = carCodeData === null ? '' : carCodeData;

  const myCarKeysWithPrice = ['trim', 'engine', 'bodyType', 'wheelDrive', 'exteriorColor'];

  const calclPrice =
    myCarKeysWithPrice.reduce((acc, cur) => acc + myCar[cur].price, 0) +
    myCar.options.reduce((acc, cur) => acc + cur.additionalPrice, 0);

  const prevPrice = useRef(calclPrice);

  const [isSavingNow, setIsSavingNow] = useState(false);
  if (isSavingNow) {
    setTimeout(() => {
      setIsSavingNow(false);
    }, 2000);
  }

  function setAutoSaving() {
    setIsSavingNow(true);
  }

  const totalPrice = useCountPrice({
    prevPrice: prevPrice.current,
    nextPrice: calclPrice,
  });

  prevPrice.current = totalPrice;

  function handleTrim({ key, option, price, id }: { key: string; option: string; price: number; id: number }) {
    setMyCar(prev => ({ ...prev, [key]: { title: option, price, id } }));
  }

  function handleOuterColor({ color, colorImage, price }: { color: string; colorImage: string; price: number }) {
    setMyCar(prev => ({ ...prev, outerColor: { title: color, imageUrl: colorImage, price } }));
  }

  function handleInnerColor({ color, colorImage, id }: { color: string; colorImage: string; id: number }) {
    setMyCar(prev => ({ ...prev, innerColor: { title: color, imageUrl: colorImage, id } }));
  }

  function handleCarImageUrl(carImageUrl: string) {
    setMyCar(prev => ({ ...prev, carImageUrl }));
  }

  function addOption({ id, name, price, imageUrl, subOptions, path }: OptionContextProps) {
    setMyCar(prev => ({
      ...prev,
      options: [...prev.options, { id, name, price, additionalPrice, imageUrl, subOptions, path }],
    }));
  }

  function removeOption(name: string) {
    setMyCar(prev => ({ ...prev, options: prev.options.filter(options => options.name !== name) }));
  }

  function clearHGenuineAccessories() {
    const clearedOptions = myCar.options.filter(option => option.path !== '/option/h-genuine-accessories');

    setMyCar(prev => ({ ...prev, options: clearedOptions }));
  }

  function handleLocalStorage() {
    localStorage.setItem('myCar', JSON.stringify(myCar));
  }

  useEffect(() => {
    const myCarData = localStorage.getItem('myCar');

    if (myCarData === null) {
      return;
    }
    const savedOptions: MyCarProps = JSON.parse(myCarData);
    setMyCar(savedOptions);
  }, []);

  return (
    <Styled.Container isFull={checkIsResultPage(pathname)}>
      <Header isSaving={isSavingNow} />
      <Navigation />
      <Styled.Wrapper isFull={checkIsResultPage(pathname)}>
        <Suspense fallback={<Loading />}>
          <Outlet
            context={{
              myCar,
              carCode,
              totalPrice,
              handleTrim,
              handleOuterColor,
              handleInnerColor,
              handleCarImageUrl,
              addOption,
              removeOption,
              clearHGenuineAccessories,
            }}
          />
        </Suspense>
      </Styled.Wrapper>
      <Footer
        myCarData={myCar}
        setDisplayAutoSaving={setAutoSaving}
        totalPrice={totalPrice}
        onSetLocalStorage={handleLocalStorage}
        carCode={carCode}
        clearHGenuineAccessories={clearHGenuineAccessories}
      />
    </Styled.Container>
  );
}
