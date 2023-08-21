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
  const { bodyTypes } = useFetchSuspense<BodyTypeDataProps>({
    fetcher: () => fetcher<BodyTypeDataProps>({ url: apiPath.bodyType(1) }),
    key: cacheKey.bodyType(1),
  });
  const [selectedIndex, handleSetIndex] = useSelectIndex();

  const { imageUrl, name, additionalPrice } = bodyTypes[selectedIndex];

  const {
    dispatch,
    myCar: { bodyType },
  } = useOutletContext<MyCarLayoutContextProps>();

  function handleCardClick(name: string, additionalPrice: number, id: number, index: number) {
    return function () {
      handleSetIndex(index)();
      dispatch({ type: MyCarActionType.TRIM_OPTION, props: { key: 'bodyType', name, additionalPrice, id } });
    };
  }

  useEffect(() => {
    if (bodyType.name === '') {
      const { name, additionalPrice, id } = bodyTypes[0];

      dispatch({ type: MyCarActionType.TRIM_OPTION, props: { key: 'bodyType', name, additionalPrice, id } });

      return;
    }

    const index = bodyTypes.findIndex(({ name }) => name === bodyType.name);
    index !== -1 && handleSetIndex(index)();
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
