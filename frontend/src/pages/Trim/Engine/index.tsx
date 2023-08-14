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
      imageUrl: '',
    },
  ],
};

export function Engine() {
  const {
    data: { engines },
  } = useFetch<EngineDataProps>({
    defaultValue: initialData,
    url: '/car/model/1/engine',
  });
  const initEngines = engines[0];

  const [selectedIndex, handleSetIndex] = useSelectIndex();
  const { imageUrl, additionalPrice, name } = engines[selectedIndex];

  const {
    handleTrim,
    trim: { engine },
  } = useOutletContext<MyCarLayoutContextProps>();

  function handleCardClick(index: number, extraCharge: number) {
    return () => {
      handleSetIndex(index)();
      handleTrim({ key: 'engine', option: engines[index].name, price: extraCharge });
    };
  }

  useEffect(() => {
    if (engine.title === '') {
      handleTrim({ key: 'engine', option: initEngines.name, price: initEngines.additionalPrice });

      return;
    }

    const index = engines.findIndex(card => card.name === engine.title);
    index !== -1 && handleSetIndex(index)();
  }, [engines]);

  return (
    <style.Container>
      <style.Wrapper>
        <style.Box>
          <MyCarImageBox hasOption={false} images={imageUrl} />
          <MyCarDescription title={name} price={additionalPrice} hasTag={false} />
        </style.Box>
        <style.CardBox>
          {engines.map(({ name, additionalPrice, description, maximumPower, maximumTorque, id }, index) => (
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
