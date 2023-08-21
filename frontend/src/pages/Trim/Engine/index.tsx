import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { EngineDataProps, MyCarLayoutContextProps } from '@/types/trim';
import { fetcher } from '@/utils/fetcher';
import { MyCarActionType, apiPath, cacheKey } from '@/constants';
import { useSelectIndex } from '@/hooks/useSelectedIndex';
import { useFetchSuspense } from '@/hooks/useFetchSuspense';

import { TrimCard } from '@/components/common/TrimCard';
import { MyCarImageBox } from '@/components/Trim/MyCarImageBox';
import { MyCarDescription } from '@/components/Trim/MyCarDescription';

import * as Styled from './style';

export function Engine() {
  const {
    dispatch,
    myCar: { engine },
  } = useOutletContext<MyCarLayoutContextProps>();

  const [selectedIndex, handleSetIndex] = useSelectIndex();

  const { engines } = useFetchSuspense<EngineDataProps>({
    fetcher: () => fetcher<EngineDataProps>({ url: apiPath.engine(1) }),
    key: cacheKey.engine(1),
  });

  const { imageUrl, additionalPrice, name } = engines[selectedIndex];

  function handleCardClick(additionalPrice: number, id: number, name: string, index: number) {
    return () => {
      handleSetIndex(index)();
      dispatch({ type: MyCarActionType.TRIM_OPTION, props: { key: 'engine', name, additionalPrice, id } });
    };
  }

  useEffect(() => {
    if (engine.name === '') {
      const { name, additionalPrice, id } = engines[0];

      dispatch({ type: MyCarActionType.TRIM_OPTION, props: { key: 'engine', name, additionalPrice, id } });

      return;
    }

    const index = engines.findIndex(({ name }) => name === engine.name);
    index !== -1 && handleSetIndex(index)();
  }, []);

  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Box>
          <MyCarImageBox hasOption={false} images={imageUrl} />
          <MyCarDescription title={name} price={additionalPrice} />
        </Styled.Box>
        <Styled.CardBox>
          {engines.map(({ name, additionalPrice, description, maximumPower, maximumTorque, id }, index) => (
            <Styled.Enclosure key={id} onClick={handleCardClick(additionalPrice, id, name, index)}>
              <TrimCard
                isActive={index === selectedIndex}
                title={name}
                price={additionalPrice}
                description={description}
                hasEngineInfo={true}
                power={maximumPower}
                torque={maximumTorque}
              />
            </Styled.Enclosure>
          ))}
        </Styled.CardBox>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
