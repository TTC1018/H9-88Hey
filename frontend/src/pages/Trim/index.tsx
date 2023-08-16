import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { MyCarLayoutContextProps, TrimDataProps } from '@/types/trim';
import { useFetch } from '@/hooks/useFetch';
import { useCacheQuery } from '@/hooks/useCacheQuery';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { SelectOptionCard } from '@/components/Trim/SelectOptionCard';
import { TrimCarImageBox } from '@/components/Trim/TrimCarImageBox';

import * as Styled from './style';

const trimInitialData = {
  trims: [
    {
      id: 0,
      name: '',
      price: 0,
      trimFeatures: [],
    },
  ],
};
export function Trim() {
  const {
    data: { trims },
  } = useFetch<Pick<TrimDataProps, 'trims'>>({ defaultValue: trimInitialData, url: '/car/model/1/trim' });

  // const {
  //   data: { trims },
  // } = useCacheQuery<Pick<TrimDataProps, 'trims'>>({
  //   defaultValue: trimInitialData,
  //   url: '/car/model/1/trim',
  //   stateTime: 10000,
  //   key: 'trims',
  // });
  const [selectedIndex, handleSetIndex] = useSelectIndex();

  const {
    handleTrim,
    myCar: { trim },
  } = useOutletContext<MyCarLayoutContextProps>();

  function handleCardClick(index: number, price: number) {
    return () => {
      handleSetIndex(index)();
      handleTrim({ key: 'trim', option: trims[index].name, price: price });
    };
  }

  useEffect(() => {
    if (trim.title === '') {
      const { name, price } = trims[0];

      handleTrim({ key: 'trim', option: name, price: price });

      return;
    }

    const index = trims.findIndex(({ name }) => name === trim.title);
    index !== -1 && handleSetIndex(index)();
  }, [trims]);

  return (
    <Styled.Container>
      <TrimCarImageBox />
      <Styled.Wrapper>
        {trims.map(({ id, name, price, trimFeatures }, index) => (
          <Styled.Box key={id} onClick={handleCardClick(index, price)}>
            <SelectOptionCard isActive={index === selectedIndex} name={name} price={price} features={trimFeatures} />
          </Styled.Box>
        ))}
      </Styled.Wrapper>
    </Styled.Container>
  );
}
