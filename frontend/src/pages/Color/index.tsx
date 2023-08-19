import { useEffect, useState } from 'react';

import { useOutletContext } from 'react-router-dom';

import { CarCodeProps, MyCarLayoutContextProps } from '@/types/trim';
import { ColorDataProps, InteriorColorsProps } from '@/types/color';
import { useFetch } from '@/hooks/useFetch';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { CheckIcon } from '@/components/Color/CheckIcon';
import { ExternalCarImage } from '@/components/Color/ExternalCarImage';
import { MyCarDescription } from '@/components/common/MyCarDescription';
import { InnerCarImage } from '@/components/Color/InnerCarImage';

import * as Styled from './style';
import { MyCarActionType } from '@/constants';

const initialData = {
  exteriorColors: [
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
  interiorColors: [
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
  const {
    dispatch,
    carCode,
    myCar: { trim, engine, wheelDrive, bodyType, exteriorColor, interiorColor },
  } = useOutletContext<MyCarLayoutContextProps>();

  const { data } = useFetch<ColorDataProps>({ defaultValue: initialData, url: '/car/color?trim_id=1' });

  const { data: carCodeData } = useFetch<CarCodeProps>({
    defaultValue: { carCode: '' },
    url: `/car/car-code?trim_id=${trim.id}&engine_id=${engine.id}&body_type_id=${bodyType.id}&wheel_drive_id=${wheelDrive.id}`,
  });

  const [isExternalPage, setIsExternalPage] = useState(true);

  const [selectedExternalIndex, handleSetExternalIndex] = useSelectIndex();
  const [selectedInnerIndex, handleSetInnerIndex] = useSelectIndex();

  const {
    name: externalName,
    carImagePath: externalCarImage,
    availableInteriorColors,
    // tags: externalTags,
    additionalPrice,
  } = data.exteriorColors[selectedExternalIndex];

  const availableInnerColorList = data.interiorColors.filter(color => availableInteriorColors.includes(color.id));
  const {
    name: innerName,
    carImageUrl: innerCarImage,
    // tags: innerTags
  } = availableInnerColorList[selectedInnerIndex];

  function updateOuterColor(index: number) {
    const { name, colorImageUrl, additionalPrice, carImagePath } = data.exteriorColors[index];

    dispatch({
      type: MyCarActionType.EXTERIOR_COLOR,
      props: {
        name,
        colorImageUrl,
        additionalPrice,
      },
    });
    dispatch({ type: MyCarActionType.CAR_IMAGE_URL, props: `${carImagePath}001.png` });
  }

  function updateInnerColor(list: InteriorColorsProps[], index: number) {
    const { name, colorImageUrl, id } = list[index];

    dispatch({
      type: MyCarActionType.INTERIOR_COLOR,
      props: {
        name,
        colorImageUrl,
        id,
      },
    });
  }

  useEffect(() => {
    const outerIndex = data.exteriorColors.findIndex(color => color.name === exteriorColor.name);
    let innerIndex = -1;

    if (outerIndex !== -1) {
      innerIndex = data.exteriorColors[outerIndex].availableInteriorColors.findIndex(
        colorId => colorId === interiorColor.id
      );

      handleSetExternalIndex(outerIndex)();
      innerIndex !== -1 && handleSetInnerIndex(innerIndex)();
    }
    if (exteriorColor.name === '') {
      updateOuterColor(0);
      updateInnerColor(availableInnerColorList, 0);
    }
  }, [data]);

  useEffect(() => {
    if (carCodeData.carCode !== '') {
      carCode.current = carCodeData.carCode;
    }
  }, [carCodeData]);

  function handleClickExternalColor(index: number) {
    handleSetExternalIndex(index)();
    handleSetInnerIndex(0)();
    updateOuterColor(index);

    const newAvailableInnerColorList = data.interiorColors.filter(color =>
      data.exteriorColors[index].availableInteriorColors.includes(color.id)
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

  return (
    <Styled.Container>
      <Styled.Wrapper>
        {isExternalPage ? <ExternalCarImage imageUrl={externalCarImage} /> : <InnerCarImage imageUrl={innerCarImage} />}
        <MyCarDescription title={descriptionTitle} price={descriptionPrice} hasTag={false} />
      </Styled.Wrapper>
      <Styled.Wrapper>
        <Styled.Box>
          <Styled.TitleBox>
            <Styled.Title>외장 색상</Styled.Title>
            <Styled.ColorName>{externalName}</Styled.ColorName>
          </Styled.TitleBox>
          <Styled.Division />
          <Styled.ColorBox>
            {data.exteriorColors.map(({ name, colorImageUrl, additionalPrice }, index) => (
              <Styled.ColorCard key={name} onClick={() => handleClickExternalColor(index)}>
                <Styled.ColorCardRect colorUrl={colorImageUrl} isActive={isSelectedExternalColor(name)} />
                <Styled.ColorCardName>{name}</Styled.ColorCardName>
                {additionalPrice > 0 && (
                  <Styled.ColorCardName>(+{additionalPrice.toLocaleString()}원)</Styled.ColorCardName>
                )}
                {isSelectedExternalColor(name) && <CheckIcon isInnerColorIcon={true} />}
              </Styled.ColorCard>
            ))}
          </Styled.ColorBox>
        </Styled.Box>
        <Styled.Box>
          <Styled.TitleBox>
            <Styled.Title>내장 색상</Styled.Title>
            <Styled.ColorName>{innerName}</Styled.ColorName>
          </Styled.TitleBox>
          <Styled.Division />
          <Styled.InteriorColorBox>
            {availableInnerColorList.map(({ name, colorImageUrl }, index) => (
              <Styled.InteriorColorCard key={name} onClick={() => handleClickInnerColor(index)}>
                <Styled.InteriorColorButton isActive={isSelectedInnerColor(name)} bgImage={colorImageUrl} />
                {isSelectedInnerColor(name) && <CheckIcon isInnerColorIcon={false} />}
              </Styled.InteriorColorCard>
            ))}
          </Styled.InteriorColorBox>
        </Styled.Box>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
