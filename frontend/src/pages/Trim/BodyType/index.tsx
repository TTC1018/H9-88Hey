import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { BodyTypeDataProps, MyCarLayoutContextProps } from '@/types/trim';
import { useFetch } from '@/hooks/useFetch';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { TrimCard } from '@/components/common/TrimCard';
import { MyCarImageBox } from '@/components/common/MyCarImageBox';
import { MyCarDescription } from '@/components/common/MyCarDescription';

import * as Styled from './style';

const initialData = {
  bodyTypes: [
    {
      id: 0,
      name: '',
      additionalPrice: 0,
      description: '',
      imageURLs: [''],
    },
  ],
};

export function BodyType() {
  const {
    data: { bodyTypes },
  } = useFetch<BodyTypeDataProps>({
    defaultValue: initialData,
    url: '/model/1/body-type',
  });
  const initBodyTypes = bodyTypes[0];

  const [selectedIndex, handleSetIndex] = useSelectIndex();
  const [selectedImageIndex, handleSetImageIndex] = useSelectIndex();

  const { imageURLs, name, additionalPrice } = bodyTypes[selectedIndex];

  const {
    handleTrim,
    trim: { bodyType },
  } = useOutletContext<MyCarLayoutContextProps>();

  function handleCardClick(index: number, extraCharge: number) {
    return function () {
      handleSetIndex(index)();
      handleSetImageIndex(0)();
      handleTrim({ key: 'bodyType', option: bodyTypes[index].name, price: extraCharge });
    };
  }

  useEffect(() => {
    if (bodyType.title === '') {
      handleTrim({ key: 'bodyType', option: initBodyTypes.name, price: initBodyTypes.additionalPrice });

      return;
    }

    const index = bodyTypes.findIndex(card => card.name === bodyType.title);
    index !== -1 && handleSetIndex(index)();
  }, [bodyTypes]);

  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Box>
          <MyCarImageBox
            hasOption={true}
            images={imageURLs}
            selectedIndex={selectedImageIndex}
            onClick={handleSetImageIndex}
          />
          <MyCarDescription title={name} price={additionalPrice} hasTag={false} />
        </Styled.Box>
        <Styled.Box>
          {bodyTypes.map(({ name, additionalPrice, description }, index) => (
            <Styled.Enclosure key={name} onClick={handleCardClick(index, additionalPrice)}>
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
