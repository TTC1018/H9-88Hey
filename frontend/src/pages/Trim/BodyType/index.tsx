import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { fetcher } from '@/utils/fetcher';
import { useSelectIndex } from '@/hooks/useSelectedIndex';
import { useFetchSuspense } from '@/hooks/useFetchSuspense';
import { MyCarActionType, apiPath, cacheKey } from '@/constants';
import { BodyTypeDataProps, MyCarLayoutContextProps } from '@/types/trim';

import { TrimCard } from '@/components/common/TrimCard';
import { MyCarImageBox } from '@/components/Trim/MyCarImageBox';
import { MyCarDescription } from '@/components/Trim/MyCarDescription';

import * as Styled from './style';

export function BodyType() {
  const {
    dispatch,
    myCar: { bodyType },
  } = useOutletContext<MyCarLayoutContextProps>();

  const { bodyTypes } = useFetchSuspense<BodyTypeDataProps>({
    fetcher: () => fetcher<BodyTypeDataProps>({ url: apiPath.bodyType(1) }),
    key: cacheKey.bodyType(1),
  });

  const index = bodyTypes.findIndex(({ name }) => name === bodyType.name);
  const initialIndex = index !== -1 ? index : 0;
  const [selectedIndex, handleSetIndex] = useSelectIndex({ initialIndex });

  const { imageUrl, name, additionalPrice } = bodyTypes[selectedIndex];

  useEffect(() => {
    if (bodyType.name === '') {
      const { name, additionalPrice, id } = bodyTypes[0];

      dispatch({ type: MyCarActionType.TRIM_OPTION, payload: { key: 'bodyType', name, additionalPrice, id } });

      return;
    }
  }, [bodyTypes]);

  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Box>
          <MyCarImageBox hasOption={false} images={imageUrl} />
          <MyCarDescription title={name} price={additionalPrice} />
        </Styled.Box>
        <Styled.Box>
          {bodyTypes.map(({ name, additionalPrice, description, id }, index) => (
            <TrimCard
              key={id}
              dispatchKey="bodyType"
              id={id}
              isActive={index === selectedIndex}
              title={name}
              price={additionalPrice}
              description={description}
              hasEngineInfo={false}
              onSetIndex={handleSetIndex(index)}
            />
          ))}
        </Styled.Box>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
