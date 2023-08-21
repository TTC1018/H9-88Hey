import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { fetcher } from '@/utils/fetcher';
import { useSelectIndex } from '@/hooks/useSelectedIndex';
import { useFetchSuspense } from '@/hooks/useFetchSuspense';
import { MyCarActionType, apiPath, cacheKey } from '@/constants';
import { MyCarLayoutContextProps, WheelDriveDataProps } from '@/types/trim';

import { TrimCard } from '@/components/common/TrimCard';
import { MyCarImageBox } from '@/components/Trim/MyCarImageBox';
import { MyCarDescription } from '@/components/Trim/MyCarDescription';

import * as Styled from './style';

export function WheelDrive() {
  const { wheelDrives } = useFetchSuspense<WheelDriveDataProps>({
    fetcher: () => fetcher<WheelDriveDataProps>({ url: apiPath.wheelDrive(1) }),
    key: cacheKey.wheelDrive(1),
  });

  const [selectedIndex, handleSetIndex] = useSelectIndex();
  const { name, additionalPrice, imageUrl } = wheelDrives[selectedIndex];

  const {
    dispatch,
    myCar: { wheelDrive },
  } = useOutletContext<MyCarLayoutContextProps>();

  function handleCardClick(name: string, additionalPrice: number, id: number, index: number) {
    return () => {
      handleSetIndex(index)();
      dispatch({ type: MyCarActionType.TRIM_OPTION, props: { key: 'wheelDrive', name, additionalPrice, id } });
    };
  }

  useEffect(() => {
    if (wheelDrive.name === '') {
      const { name, additionalPrice, id } = wheelDrives[0];

      dispatch({ type: MyCarActionType.TRIM_OPTION, props: { key: 'wheelDrive', name, additionalPrice, id } });

      return;
    }

    const index = wheelDrives.findIndex(({ name }) => name === wheelDrive.name);
    index !== -1 && handleSetIndex(index)();
  }, []);

  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Box>
          <MyCarImageBox hasOption={false} images={imageUrl} />
          <MyCarDescription title={name} price={additionalPrice} />
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
