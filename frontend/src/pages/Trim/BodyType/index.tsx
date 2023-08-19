import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { MyCarActionType } from '@/constants';
import { fetcher } from '@/utils/fetcher';
import { useFetchSuspense } from '@/hooks/useFetchSuspense';
import { useSelectIndex } from '@/hooks/useSelectedIndex';
import { BodyTypeDataProps, MyCarLayoutContextProps } from '@/types/trim';

import { TrimCard } from '@/components/common/TrimCard';
import { MyCarImageBox } from '@/components/Trim/MyCarImageBox';
import { MyCarDescription } from '@/components/common/MyCarDescription';

import * as Styled from './style';

function bodyTypeFetcher() {
  return fetcher<BodyTypeDataProps>({ url: '/car/model/1/body-type' });
}

export function BodyType() {
  const { bodyTypes } = useFetchSuspense<BodyTypeDataProps>({
    fetcher: bodyTypeFetcher,
    key: ['bodyTypes'],
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
          <MyCarDescription title={name} price={additionalPrice} hasTag={false} />
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
