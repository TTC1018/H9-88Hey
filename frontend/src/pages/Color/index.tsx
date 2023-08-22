import { useEffect, useState } from 'react';

import { useOutletContext } from 'react-router-dom';

import { fetcher } from '@/utils/fetcher';
import { MyCarActionType, apiPath, cacheKey } from '@/constants';
import { useSelectIndex } from '@/hooks/useSelectedIndex';
import { useFetchSuspense } from '@/hooks/useFetchSuspense';
import { ColorDataProps, InteriorColorsProps } from '@/types/color';
import { CarCodeProps, MyCarLayoutContextProps } from '@/types/trim';

import { CheckIcon } from '@/components/Color/CheckIcon';
import { InnerCarImage } from '@/components/Color/InnerCarImage';
import { ExternalCarImage } from '@/components/Color/ExternalCarImage';
import { MyCarDescription } from '@/components/Color/MyCarDescription';

import * as Styled from './style';

export function Color() {
  const {
    dispatch,
    carCode,
    myCar: { trim, engine, wheelDrive, bodyType, exteriorColor, interiorColor },
  } = useOutletContext<MyCarLayoutContextProps>();

  const { exteriorColors, interiorColors } = useFetchSuspense<ColorDataProps>({
    fetcher: () => fetcher<ColorDataProps>({ url: apiPath.color(trim.id) }),
    key: cacheKey.color(trim.id),
  });

  const { carCode: carCodeData } = useFetchSuspense<CarCodeProps>({
    fetcher: () =>
      fetcher<CarCodeProps>({
        url: apiPath.carCode(trim.id, engine.id, bodyType.id, wheelDrive.id),
      }),
    key: cacheKey.carCode(trim.id, engine.id, bodyType.id, wheelDrive.id),
  });

  const [isExternalPage, setIsExternalPage] = useState(true);

  const exteriorIndex = exteriorColors.findIndex(color => color.name === exteriorColor.name);
  const exteriorInitialIndex = exteriorIndex !== -1 ? exteriorIndex : 0;
  const interiorIndex = exteriorColors[exteriorInitialIndex].availableInteriorColors.findIndex(
    colorId => colorId === interiorColor.id
  );
  const InteriorInitialIndex = interiorIndex !== -1 ? interiorIndex : 0;
  const [selectedExternalIndex, handleSetExternalIndex] = useSelectIndex({ initialIndex: exteriorInitialIndex });
  const [selectedInnerIndex, handleSetInnerIndex] = useSelectIndex({ initialIndex: InteriorInitialIndex });

  const {
    id: exteriorColorId,
    name: externalName,
    carImagePath: externalCarImage,
    availableInteriorColors,
    additionalPrice,
  } = exteriorColors[selectedExternalIndex];

  const availableInnerColorList = interiorColors.filter(color => availableInteriorColors.includes(color.id));
  const {
    id: interiorColorId,
    name: innerName,
    carImageUrl: innerCarImage,
  } = availableInnerColorList[selectedInnerIndex];

  function updateOuterColor(index: number) {
    const { name, colorImageUrl, additionalPrice, carImagePath } = exteriorColors[index];

    dispatch({
      type: MyCarActionType.EXTERIOR_COLOR,
      payload: {
        name,
        colorImageUrl,
        additionalPrice,
        carImagePath,
      },
    });
  }

  function updateInnerColor(list: InteriorColorsProps[], index: number) {
    const { name, colorImageUrl, id } = list[index];

    dispatch({
      type: MyCarActionType.INTERIOR_COLOR,
      payload: {
        name,
        colorImageUrl,
        id,
      },
    });
  }

  useEffect(() => {
    if (exteriorColor.name === '') {
      updateOuterColor(0);
      updateInnerColor(availableInnerColorList, 0);
    }
  }, [interiorColor, exteriorColor]);

  useEffect(() => {
    if (carCodeData !== '') {
      carCode.current = carCodeData;
    }
  }, [carCodeData]);

  function handleClickExternalColor(index: number) {
    handleSetExternalIndex(index)();
    handleSetInnerIndex(0)();
    updateOuterColor(index);

    const newAvailableInnerColorList = interiorColors.filter(color =>
      exteriorColors[index].availableInteriorColors.includes(color.id)
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
      <Styled.ImageWrapper>
        {isExternalPage ? <ExternalCarImage imageUrl={externalCarImage} /> : <InnerCarImage imageUrl={innerCarImage} />}
        <MyCarDescription
          title={descriptionTitle}
          price={descriptionPrice}
          isExterirorColor={isExternalPage}
          tagId={isExternalPage ? exteriorColorId : interiorColorId}
        />
      </Styled.ImageWrapper>
      <Styled.ColorWrapper>
        <Styled.ColorEnclosure>
          <Styled.Box>
            <Styled.TitleBox>
              <Styled.Title>외장 색상</Styled.Title>
              <Styled.ColorName>{externalName}</Styled.ColorName>
            </Styled.TitleBox>
            <Styled.Division />
            <Styled.ColorBox>
              {exteriorColors.map(({ name, colorImageUrl, additionalPrice }, index) => (
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
        </Styled.ColorEnclosure>
      </Styled.ColorWrapper>
    </Styled.Container>
  );
}
