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

  const { engines } = useFetchSuspense<EngineDataProps>({
    fetcher: () => fetcher<EngineDataProps>({ url: apiPath.engine(1) }),
    key: cacheKey.engine(1),
  });

  const index = engines.findIndex(({ name }) => name === engine.name);
  const initialIndex = index !== -1 ? index : 0;
  const [selectedIndex, handleSetIndex] = useSelectIndex({ initialIndex });

  const { imageUrl, additionalPrice, name } = engines[selectedIndex];

  useEffect(() => {
    if (engine.name === '') {
      const { name, additionalPrice, id } = engines[0];

      dispatch({ type: MyCarActionType.TRIM_OPTION, payload: { key: 'engine', name, additionalPrice, id } });

      return;
    }
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
            <TrimCard
              key={id}
              dispatchKey="engine"
              id={id}
              isActive={index === selectedIndex}
              title={name}
              price={additionalPrice}
              description={description}
              hasEngineInfo={true}
              power={maximumPower}
              torque={maximumTorque}
              onSetIndex={handleSetIndex(index)}
            />
          ))}
        </Styled.CardBox>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
