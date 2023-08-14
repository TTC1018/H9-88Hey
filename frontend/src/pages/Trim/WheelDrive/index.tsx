import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { MyCarLayoutContextProps, WheelDriveDataProps } from '@/types/trim';
import { useFetch } from '@/hooks/useFetch';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { TrimCard } from '@/components/common/TrimCard';
import { MyCarImageBox } from '@/components/common/MyCarImageBox';
import { MyCarDescription } from '@/components/common/MyCarDescription';

import * as style from './style';

const initialData = {
  wheelDrives: [
    {
      id: 0,
      name: '',
      additionalPrice: 0,
      description: '',
      imageUrl: '',
    },
  ],
};

export function WheelDrive() {
  const {
    data: { wheelDrives },
  } = useFetch<WheelDriveDataProps>({
    defaultValue: initialData,
    url: '/car/model/1/wheel-drive',
  });
  const initwheelDrives = wheelDrives[0];

  const [selectedIndex, handleSetIndex] = useSelectIndex();
  const { name, additionalPrice, imageUrl } = wheelDrives[selectedIndex];

  const {
    handleTrim,
    trim: { wheelDrive },
  } = useOutletContext<MyCarLayoutContextProps>();

  function handleCardClick(index: number, additionalPrice: number) {
    return () => {
      handleSetIndex(index)();
      handleTrim({ key: 'wheelDrive', option: wheelDrives[index].name, price: additionalPrice });
    };
  }

  useEffect(() => {
    if (wheelDrive.title === '') {
      handleTrim({ key: 'wheelDrive', option: initwheelDrives.name, price: initwheelDrives.additionalPrice });

      return;
    }

    const index = wheelDrives.findIndex(card => card.name === wheelDrive.title);
    index !== -1 && handleSetIndex(index)();
  }, [wheelDrives]);

  return (
    <style.Container>
      <style.Wrapper>
        <style.Box>
          <MyCarImageBox hasOption={false} images={imageUrl} />
          <MyCarDescription title={name} price={additionalPrice} hasTag={false} />
        </style.Box>
        <style.Box>
          {wheelDrives.map(({ name, additionalPrice, description, id }, index) => (
            <style.Enclosure key={id} onClick={handleCardClick(index, additionalPrice)}>
              <TrimCard
                isActive={index === selectedIndex}
                title={name}
                price={additionalPrice}
                description={description}
                hasEngineInfo={false}
              />
            </style.Enclosure>
          ))}
        </style.Box>
      </style.Wrapper>
    </style.Container>
  );
}
