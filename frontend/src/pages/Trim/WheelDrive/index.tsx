import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { MyCarLayoutContextProps, WheelDriveDataProps } from '@/types/trim';
import { useFetch } from '@/hooks/useFetch';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { TrimCard } from '@/components/common/TrimCard';
import { MyCarImageBox } from '@/components/Trim/MyCarImageBox';
import { MyCarDescription } from '@/components/common/MyCarDescription';

import * as Styled from './style';

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

  const [selectedIndex, handleSetIndex] = useSelectIndex();
  const { name, additionalPrice, imageUrl } = wheelDrives[selectedIndex];

  const {
    handleTrimOption,
    myCar: { wheelDrive },
  } = useOutletContext<MyCarLayoutContextProps>();

  function handleCardClick(name: string, additionalPrice: number, id: number, index: number) {
    return () => {
      handleSetIndex(index)();
      handleTrimOption({ key: 'wheelDrive', name, additionalPrice, id });
    };
  }

  useEffect(() => {
    if (wheelDrive.name === '') {
      const { name, additionalPrice, id } = wheelDrives[0];

      handleTrimOption({ key: 'wheelDrive', name, additionalPrice, id });

      return;
    }

    const index = wheelDrives.findIndex(({ name }) => name === wheelDrive.name);
    index !== -1 && handleSetIndex(index)();
  }, [wheelDrives]);

  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Box>
          <MyCarImageBox hasOption={false} images={imageUrl} />
          <MyCarDescription title={name} price={additionalPrice} hasTag={false} />
        </Styled.Box>
        <Styled.Box>
          {wheelDrives.map(({ name, additionalPrice, description, id }, index) => (
            <Styled.Enclosure key={id} onClick={handleCardClick(name, additionalPrice, id, index)}>
              <TrimCard
                isActive={index === selectedIndex}
                title={name}
                price={additionalPrice}
                description={description}
                hasEngineInfo={false}
              />
            </Styled.Enclosure>
          ))}
        </Styled.Box>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
