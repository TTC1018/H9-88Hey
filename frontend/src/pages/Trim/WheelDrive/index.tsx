import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { MyCarLayoutContextType, WheelDriveDataType } from '@/types/trim';
import { useFetch } from '@/hooks/useFetch';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { TrimCard } from '@/components/common/TrimCard';
import { MyCarImageBox } from '@/components/common/MyCarImageBox';
import { MyCarDescription } from '@/components/common/MyCarDescription';

import * as Styled from './style';

const initialData = {
  wheelDrive: [
    {
      name: '',
      extraCharge: 0,
      desc: '',
      image: '',
    },
  ],
};

export function WheelDrive() {
  const { data } = useFetch<WheelDriveDataType>({
    defaultValue: initialData,
    url: '/model/palisade/trim/le_blanc/wheel_drive',
  });

  const [selectedIndex, handleSetIndex] = useSelectIndex();
  const { name, extraCharge, image } = data.wheelDrive[selectedIndex];

  const {
    handleTrim,
    trim: { wheelDrive },
  } = useOutletContext<MyCarLayoutContextType>();

  function handleCardClick(index: number, extraCharge: number) {
    return () => {
      handleSetIndex(index)();
      handleTrim({ key: 'wheelDrive', option: data.wheelDrive[index].name, price: extraCharge });
    };
  }

  useEffect(() => {
    const index = data.wheelDrive.findIndex(card => card.name === wheelDrive.title);

    index !== -1 && handleSetIndex(index)();
    wheelDrive.title === '' &&
      handleTrim({ key: 'wheelDrive', option: data.wheelDrive[0].name, price: data.wheelDrive[0].extraCharge });
  }, [data]);

  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Box>
          <MyCarImageBox hasOption={false} images={image} />
          <MyCarDescription title={name} price={extraCharge} hasTag={false} />
        </Styled.Box>
        <Styled.Box>
          {data.wheelDrive.map(({ name, extraCharge, desc }, index) => (
            <Styled.Enclosure key={name} onClick={handleCardClick(index, extraCharge)}>
              <TrimCard
                isActive={index === selectedIndex}
                title={name}
                price={extraCharge}
                description={desc}
                hasEngineInfo={false}
              />
            </Styled.Enclosure>
          ))}
        </Styled.Box>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
