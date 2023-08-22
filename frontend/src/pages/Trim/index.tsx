import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { fetcher } from '@/utils/fetcher';
import { useSelectIndex } from '@/hooks/useSelectedIndex';
import { useFetchSuspense } from '@/hooks/useFetchSuspense';
import { MyCarActionType, apiPath, cacheKey } from '@/constants';
import { MyCarLayoutContextProps, TrimDataProps } from '@/types/trim';

import { SelectOptionCard } from '@/components/Trim/SelectOptionCard';
import { TrimCarImageBox } from '@/components/Trim/TrimCarImageBox';

import * as Styled from './style';

export function Trim() {
  const {
    dispatch,
    myCar: { trim },
  } = useOutletContext<MyCarLayoutContextProps>();

  const { trims } = useFetchSuspense<TrimDataProps>({
    fetcher: () => fetcher<TrimDataProps>({ url: apiPath.trim(1) }),
    key: cacheKey.trim(1),
  });

  const index = trims.findIndex(({ name }) => name === trim.name);
  const initialIndex = index !== -1 ? index : 0;
  const [selectedIndex, handleSetIndex] = useSelectIndex({ initialIndex });

  function handleCardClick(name: string, price: number, id: number, index: number) {
    return () => {
      handleSetIndex(index)();
      dispatch({ type: MyCarActionType.TRIM, payload: { name, price, id } });
    };
  }

  useEffect(() => {
    if (trim.name === '') {
      const { name, price, id } = trims[0];

      dispatch({ type: MyCarActionType.TRIM, payload: { name, price, id } });

      return;
    }
  }, []);

  return (
    <Styled.Container>
      <TrimCarImageBox />
      <Styled.Wrapper>
        {trims.map(({ id, name, price, trimFeatures }, index) => (
          <Styled.Box key={id} onClick={handleCardClick(name, price, id, index)}>
            <SelectOptionCard isActive={index === selectedIndex} name={name} price={price} features={trimFeatures} />
          </Styled.Box>
        ))}
      </Styled.Wrapper>
    </Styled.Container>
  );
}
