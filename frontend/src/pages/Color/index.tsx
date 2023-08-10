import { useEffect, useState } from 'react';

import { useOutletContext } from 'react-router-dom';

import { ColorDataProps, InterierColorsProps } from '@/types/color';
import { MyCarLayoutContextProps } from '@/types/trim';
import { useFetch } from '@/hooks/useFetch';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { CheckIcon } from '@/components/common/CheckIcon';
import { ExternalCarImage } from '@/components/color/ExternalCarImage';
import { MyCarDescription } from '@/components/common/MyCarDescription';
import { InnerCarImage } from '@/components/color/InnerCarImage';

import * as style from './style';

const initialData = {
  exterierColors: [
    {
      id: 1,
      name: '',
      carImagePath: '',
      colorImageUrl: '',
      additionalPrice: 0,
      availableInteriorColors: [0],
      tags: [''],
    },
  ],
  interierColors: [
    {
      id: 0,
      name: '',
      carImageUrl: '',
      colorImageUrl: '',
      tags: [''],
    },
  ],
};

export function Color() {
  const { data } = useFetch<ColorDataProps>({ defaultValue: initialData, url: '/model/1/trim/2/color' });
  const [isExternalPage, setIsExternalPage] = useState(true);
  const [externalColorWord, setExternalColorWord] = useState('');
  const [innerColorWord, setInnerColorWord] = useState('');

  const [selectedExternalIndex, handleSetExternalIndex] = useSelectIndex();
  const [selectedInnerIndex, handleSetInnerIndex] = useSelectIndex();

  const {
    name: externalName,
    colorImageUrl: externalImageUrl,
    availableInteriorColors,
    tags: externalTags,
    additionalPrice,
  } = data.exterierColors[selectedExternalIndex];

  const availableInnerColorList = data.interierColors.filter(color => availableInteriorColors.includes(color.id));
  const {
    name: innerName,
    colorImageUrl: innerImageUrl,
    tags: innerTags,
  } = availableInnerColorList[selectedInnerIndex];

  const {
    handleOuterColor,
    handleInnerColor,
    trim: { outerColor, innerColor },
  } = useOutletContext<MyCarLayoutContextProps>();

  function updateOuterColor(index: number) {
    const selectedExteriorColor = data.exterierColors[index];

    handleOuterColor({
      color: selectedExteriorColor.name,
      colorImage: selectedExteriorColor.colorImageUrl,
      price: selectedExteriorColor.additionalPrice,
    });
  }

  function updateInnerColor(list: InterierColorsProps[], index: number) {
    const selectedInteriorColor = list[index];

    handleInnerColor({
      color: selectedInteriorColor.name,
      colorImage: selectedInteriorColor.colorImageUrl,
      id: selectedInteriorColor.id,
    });
  }

  useEffect(() => {
    const outerIndex = data.exterierColors.findIndex(color => color.name === outerColor.title);
    let innerIndex = -1;

    if (outerIndex !== -1) {
      innerIndex = data.exterierColors[outerIndex].availableInteriorColors.findIndex(
        colorId => colorId === innerColor.id
      );

      handleSetExternalIndex(outerIndex)();
      innerIndex !== -1 && handleSetInnerIndex(innerIndex)();
    }
    if (outerColor.title === '') {
      updateOuterColor(0);
      updateInnerColor(availableInnerColorList, 0);
    }
  }, [data]);

  useEffect(() => {
    if (externalImageUrl !== '') {
      setExternalColorWord(externalImageUrl.split('exterior/')[1].slice(0, 3));
    }
  }, [externalImageUrl]);

  useEffect(() => {
    if (innerImageUrl !== '') {
      setInnerColorWord(innerImageUrl.split('interior/')[1].slice(0, 3));
    }
  }, [innerImageUrl]);

  function handleClickExternalColor(index: number) {
    handleSetExternalIndex(index)();
    handleSetInnerIndex(0)();
    updateOuterColor(index);

    const newAvailableInnerColorList = data.interierColors.filter(color =>
      data.exterierColors[index].availableInteriorColors.includes(color.id)
    );

    updateInnerColor(newAvailableInnerColorList, 0);
    setIsExternalPage(true);
  }

  function handleClickInnerColor(index: number) {
    handleSetInnerIndex(index)();
    updateInnerColor(availableInnerColorList, index);
    setIsExternalPage(false);
  }

  function isSelectedExternalColor(name: string) {
    return externalName === name;
  }

  function isSelectedInnerColor(name: string) {
    return innerName === name;
  }

  const descriptionTitle = isExternalPage ? externalName : innerName;
  const descriptionPrice = isExternalPage ? additionalPrice : 0;
  const descriptionTags = isExternalPage ? externalTags : innerTags;

  return (
    <style.Container>
      <style.Wrapper>
        {isExternalPage ? <ExternalCarImage color={externalColorWord} /> : <InnerCarImage color={innerColorWord} />}
        <MyCarDescription title={descriptionTitle} price={descriptionPrice} hasTag={true} tags={descriptionTags} />
      </style.Wrapper>
      <style.Wrapper>
        <style.Box>
          <style.TitleBox>
            <style.Title>외장 색상</style.Title>
            <style.ColorName>{externalName}</style.ColorName>
          </style.TitleBox>
          <style.Division />
          <style.ColorBox>
            {data.exterierColors.map(({ name, colorImageUrl, additionalPrice }, index) => (
              <style.ColorCard key={name} onClick={() => handleClickExternalColor(index)}>
                <style.ColorCardRect colorUrl={colorImageUrl} isActive={isSelectedExternalColor(name)} />
                <style.ColorCardName>{name}</style.ColorCardName>
                {additionalPrice > 0 && (
                  <style.ColorCardName>(+{additionalPrice.toLocaleString()}원)</style.ColorCardName>
                )}
                {isSelectedExternalColor(name) && <CheckIcon isInnerColorIcon={true} />}
              </style.ColorCard>
            ))}
          </style.ColorBox>
        </style.Box>
        <style.Box>
          <style.TitleBox>
            <style.Title>내장 색상</style.Title>
            <style.ColorName>{innerName}</style.ColorName>
          </style.TitleBox>
          <style.Division />
          <style.InteriorColorBox>
            {availableInnerColorList.map(({ name, colorImageUrl }, index) => (
              <style.InteriorColorCard key={name} onClick={() => handleClickInnerColor(index)}>
                <style.InteriorColorButton isActive={isSelectedInnerColor(name)} bgImage={colorImageUrl} />
                {isSelectedInnerColor(name) && <CheckIcon isInnerColorIcon={false} />}
              </style.InteriorColorCard>
            ))}
          </style.InteriorColorBox>
        </style.Box>
      </style.Wrapper>
    </style.Container>
  );
}
