import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { MyCarLayoutContextType, TrimDataType } from '@/types/trim';
import { useFetch } from '@/hooks/useFetch';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { MyCarImageBox } from '@/components/common/MyCarImageBox';
import { SelectOptionCard } from '@/components/trim/SelectOptionCard';

import * as style from './style';

const initialData = {
  trims: [
    {
      name: '',
      price: 0,
      images: [],
      features: [],
    },
  ],
};

export function Trim() {
  const { data } = useFetch<TrimDataType>({ defaultValue: initialData, url: '/model/palisade/trim' });

  const [selectedIndex, handleSetIndex] = useSelectIndex();
  const [selectedImageIndex, handleSetImageIndex] = useSelectIndex();

  const { images } = data.trims[selectedImageIndex];

  const {
    handleTrim,
    trim: { model },
  } = useOutletContext<MyCarLayoutContextType>();

  function handleCardClick(index: number, price: number) {
    return () => {
      handleSetIndex(index)();
      handleTrim({ key: 'model', option: data.trims[index].name, price: price });
      handleSetImageIndex(0)();
    };
  }

  useEffect(() => {
    const index = data.trims.findIndex(card => card.name === model.title);

    index !== -1 && handleSetIndex(index)();
    model.title === '' && handleTrim({ key: 'model', option: data.trims[0].name, price: data.trims[0].price });
  }, [data]);

  return (
    <style.Container>
      <MyCarImageBox
        hasOption={true}
        images={images}
        selectedIndex={selectedImageIndex}
        onClick={handleSetImageIndex}
      />
      <style.Wrapper>
        {data.trims.map(({ name, price, features }, index) => (
          <style.Box key={name} onClick={handleCardClick(index, price)}>
            <SelectOptionCard isActive={index === selectedIndex} name={name} price={price} features={features} />
          </style.Box>
        ))}
      </style.Wrapper>
    </style.Container>
  );
}
