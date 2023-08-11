import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { MyCarLayoutContextProps, TrimDataProps } from '@/types/trim';
import { useFetch } from '@/hooks/useFetch';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { MyCarImageBox } from '@/components/common/MyCarImageBox';
import { SelectOptionCard } from '@/components/trim/SelectOptionCard';

import * as style from './style';

const initialData = {
  carImageUrl: [''],
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
  const { data } = useFetch<TrimDataProps>({ defaultValue: initialData, url: '/model/1/trim' });

  const [selectedIndex, handleSetIndex] = useSelectIndex();
  const [selectedImageIndex, handleSetImageIndex] = useSelectIndex();

  const {
    handleTrim,
    trim: { model },
  } = useOutletContext<MyCarLayoutContextProps>();

  function handleCardClick(index: number, price: number) {
    return () => {
      handleSetIndex(index)();
      handleTrim({ key: 'model', option: data.trims[index].name, price: price });
    };
  }

  useEffect(() => {
    if (model.title === '') {
      handleTrim({ key: 'model', option: data.trims[0].name, price: data.trims[0].price });

      return;
    }

    const index = data.trims.findIndex(card => card.name === model.title);
    index !== -1 && handleSetIndex(index)();
  }, [data]);

  return (
    <style.Container>
      <MyCarImageBox
        hasOption={true}
        images={data.carImageUrl}
        selectedIndex={selectedImageIndex}
        onClick={handleSetImageIndex}
      />
      <style.Wrapper>
        {data.trims.map(({ id, name, price, trimFeatures }, index) => (
          <style.Box key={id} onClick={handleCardClick(index, price)}>
            <SelectOptionCard isActive={index === selectedIndex} name={name} price={price} features={trimFeatures} />
          </style.Box>
        ))}
      </style.Wrapper>
    </style.Container>
  );
}
