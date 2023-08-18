import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { MyCarLayoutContextProps, TrimDataProps } from '@/types/trim';
import { useSelectIndex } from '@/hooks/useSelectedIndex';
import { useFetchSuspense } from '@/hooks/useFetchSuspense';

import { SelectOptionCard } from '@/components/Trim/SelectOptionCard';
import { TrimCarImageBox } from '@/components/Trim/TrimCarImageBox';

import * as Styled from './style';

export function Trim() {
  const { trims } = useFetchSuspense<TrimDataProps>({ url: '/car/model/1/trim', key: ['trim'], staleTime: 2000 });

  const [selectedIndex, handleSetIndex] = useSelectIndex();

  const {
    handleTrim,
    myCar: { trim },
  } = useOutletContext<MyCarLayoutContextProps>();

  function handleCardClick(name: string, price: number, id: number, index: number) {
    return () => {
      handleSetIndex(index)();
      handleTrim({ name, price, id });
    };
  }

  useEffect(() => {
    if (trim.name === '') {
      const { name, price, id } = trims[0];

      handleTrim({ name, price, id });

      return;
    }

    const index = trims.findIndex(({ name }) => name === trim.name);
    index !== -1 && handleSetIndex(index)();
  }, [trims]);

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
