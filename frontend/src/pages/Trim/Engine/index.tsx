import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { EngineDataProps, MyCarLayoutContextProps } from '@/types/trim';
import { useFetch } from '@/hooks/useFetch';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { TrimCard } from '@/components/common/TrimCard';
import { MyCarImageBox } from '@/components/common/MyCarImageBox';
import { MyCarDescription } from '@/components/common/MyCarDescription';

import * as style from './style';

const initialData = {
  engines: [
    {
      id: 0,
      name: '',
      additionalPrice: 0,
      description: '',
      maximumPower: '',
      maximumTorque: '',
      imageURL: '',
    },
  ],
};

export function Engine() {
  const { data } = useFetch<EngineDataProps>({
    defaultValue: initialData,
    url: '/model/1/engine',
  });
  console.log(data);
  const [selectedIndex, handleSetIndex] = useSelectIndex();
  const { imageURL, additionalPrice, name } = data.engines[selectedIndex];

  const {
    handleTrim,
    trim: { engine },
  } = useOutletContext<MyCarLayoutContextProps>();

  function handleCardClick(index: number, extraCharge: number) {
    return () => {
      handleSetIndex(index)();
      handleTrim({ key: 'engine', option: data.engines[index].name, price: extraCharge });
    };
  }

  useEffect(() => {
    if (engine.title === '') {
      handleTrim({ key: 'engine', option: data.engines[0].name, price: data.engines[0].additionalPrice });

      return;
    }

    const index = data.engines.findIndex(card => card.name === engine.title);
    index !== -1 && handleSetIndex(index)();
  }, [data]);

  return (
    <style.Container>
      <style.Wrapper>
        <style.Box>
          <MyCarImageBox hasOption={false} images={imageURL} />
          <MyCarDescription title={name} price={additionalPrice} hasTag={false} />
        </style.Box>
        <style.CardBox>
          {data.engines.map(({ name, additionalPrice, description, maximumPower, maximumTorque, id }, index) => (
            <style.Enclosure key={id} onClick={handleCardClick(index, additionalPrice)}>
              <TrimCard
                isActive={index === selectedIndex}
                title={name}
                price={additionalPrice}
                description={description}
                hasEngineInfo={true}
                power={maximumPower}
                torque={maximumTorque}
              />
            </style.Enclosure>
          ))}
        </style.CardBox>
      </style.Wrapper>
    </style.Container>
  );
}
