import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { EngineDataType, MyCarLayoutContextType } from '@/types/trim';
import { useFetch } from '@/hooks/useFetch';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { TrimCard } from '@/components/common/TrimCard';
import { MyCarImageBox } from '@/components/common/MyCarImageBox';
import { MyCarDescription } from '@/components/common/MyCarDescription';

import * as Styled from './style';

const initialData = {
  engines: [
    {
      name: '',
      extraCharge: 0,
      desc: '',
      maximumOutput: '',
      maximumTorque: '',
      image: '',
    },
  ],
};

export function Engine() {
  const { data } = useFetch<EngineDataType>({
    defaultValue: initialData,
    url: '/model/palisade/trim/le_blanc/engine',
  });

  const [selectedIndex, handleSetIndex] = useSelectIndex();
  const { image, extraCharge, name } = data.engines[selectedIndex];

  const {
    handleTrim,
    trim: { engine },
  } = useOutletContext<MyCarLayoutContextType>();

  function handleCardClick(index: number, extraCharge: number) {
    return () => {
      handleSetIndex(index)();
      handleTrim({ key: 'engine', option: data.engines[index].name, price: extraCharge });
    };
  }

  useEffect(() => {
    const index = data.engines.findIndex(card => card.name === engine.title);

    index !== -1 && handleSetIndex(index)();
    engine.title === '' &&
      handleTrim({ key: 'engine', option: data.engines[0].name, price: data.engines[0].extraCharge });
  }, [data]);

  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Box>
          <MyCarImageBox hasOption={false} images={image} />
          <MyCarDescription title={name} price={extraCharge} hasTag={false} />
        </Styled.Box>
        <Styled.Box>
          {data.engines.map(({ name, extraCharge, desc, maximumOutput, maximumTorque }, index) => (
            <Styled.Enclosure key={name} onClick={handleCardClick(index, extraCharge)}>
              <TrimCard
                isActive={index === selectedIndex}
                title={name}
                price={extraCharge}
                description={desc}
                hasEngineInfo={true}
                power={maximumOutput}
                torque={maximumTorque}
              />
            </Styled.Enclosure>
          ))}
        </Styled.Box>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
