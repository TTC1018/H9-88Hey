import { useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { BodyTypeDataType, MyCarLayoutContextType } from '@/types/trim';
import { useFetch } from '@/hooks/useFetch';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { TrimCard } from '@/components/common/TrimCard';
import { MyCarImageBox } from '@/components/common/MyCarImageBox';
import { MyCarDescription } from '@/components/common/MyCarDescription';

import * as style from './style';

const initialData = {
  bodyTypes: [
    {
      name: '',
      extraCharge: 0,
      desc: '',
      images: [''],
    },
  ],
};

export function BodyType() {
  const { data } = useFetch<BodyTypeDataType>({
    defaultValue: initialData,
    url: '/model/palisade/trim/le_blanc/body_type',
  });

  const [selectedIndex, handleSetIndex] = useSelectIndex();
  const [selectedImageIndex, handleSetImageIndex] = useSelectIndex();

  const { images, name, extraCharge } = data.bodyTypes[selectedIndex];

  const {
    handleTrim,
    trim: { bodyType },
  } = useOutletContext<MyCarLayoutContextType>();

  function handleCardClick(index: number, extraCharge: number) {
    return function () {
      handleSetIndex(index)();
      handleSetImageIndex(0)();
      handleTrim({ key: 'bodyType', option: data.bodyTypes[index].name, price: extraCharge });
    };
  }

  useEffect(() => {
    const index = data.bodyTypes.findIndex(card => card.name === bodyType.title);

    index !== -1 && handleSetIndex(index)();
    bodyType.title === '' &&
      handleTrim({ key: 'bodyType', option: data.bodyTypes[0].name, price: data.bodyTypes[0].extraCharge });
  }, [data]);

  return (
    <style.Container>
      <style.Wrapper>
        <style.Box>
          <MyCarImageBox
            hasOption={true}
            images={images}
            selectedIndex={selectedImageIndex}
            onClick={handleSetImageIndex}
          />
          <MyCarDescription title={name} price={extraCharge} hasTag={false} />
        </style.Box>
        <style.Box>
          {data.bodyTypes.map(({ name, extraCharge, desc }, index) => (
            <style.Enclosure key={name} onClick={handleCardClick(index, extraCharge)}>
              <TrimCard
                isActive={index === selectedIndex}
                title={name}
                price={extraCharge}
                description={desc}
                hasEngineInfo={false}
              />
            </style.Enclosure>
          ))}
        </style.Box>
      </style.Wrapper>
    </style.Container>
  );
}
