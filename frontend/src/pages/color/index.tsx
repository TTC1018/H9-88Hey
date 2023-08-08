import { useEffect, useState } from 'react';

import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { CheckIcon } from '@/components/common/CheckIcon';
import { ExternalCarImage } from '@/components/color/ExternalCarImage';
import { MyCarDescription } from '@/components/common/MyCarDescription';
import { InnerCarImage } from '@/components/color/InnerCarImage';

import * as style from './style';

const mock_data = {
  data: {
    externalColors: [
      {
        name: '어비스 블랙 펄',
        imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/A2B/colorchip-exterior.png',
        availableInnerColor: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '어비스 블랙 펄'],
        extraFee: 0,
      },
      {
        name: '쉬머링 실버 메탈릭',
        imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/R2T/colorchip-exterior.png',
        availableInnerColor: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '쉬머링 실버 메탈릭'],
        extraFee: 0,
      },
      {
        name: '문라이트 블루 펄',
        imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/UB7/colorchip-exterior.png',
        availableInnerColor: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '문라이트 블루펄'],
        extraFee: 0,
      },
      {
        name: '가이아 브라운 펄',
        imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/D2S/colorchip-exterior.png',
        availableInnerColor: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '가이아 블아운 펄'],
        extraFee: 0,
      },
      {
        name: '그라파이트 그레이 메탈릭',
        imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/P7V/colorchip-exterior.png',
        availableInnerColor: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '그라파이트 그레이'],
        extraFee: 0,
      },
      {
        name: '크리미 화이트 펄',
        imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/WC9/colorchip-exterior.png',
        availableInnerColor: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '크리미 화이트 펄'],
        extraFee: 100000,
      },
    ],
    innerColors: [
      {
        name: '퀼팅천연(블랙)',
        imagePath: 'https://www.hyundai.com/contents/vr360/LX06/interior/I49/colorchip-interior.png',
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '퀼팅천연'],
      },
      {
        name: '쿨그레이',
        imagePath: 'https://www.hyundai.com/contents/vr360/LX06/interior/YJY/colorchip-interior.png',
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '쿨그레이'],
      },
    ],
  },
};

export function Color() {
  const [isExternalPage, setIsExternalPage] = useState(true);
  const [externalColorWord, setExternalColorWord] = useState('');
  const [innerColorWord, setInnerColorWord] = useState('');

  const [selectedExternalIndex, handleSetExternalIndex] = useSelectIndex();
  const [selectedInnerIndex, handleSetInnerIndex] = useSelectIndex();

  const {
    name: externalName,
    imageUrl: externalImageUrl,
    availableInnerColor,
    tags: externalTags,
    extraFee,
  } = mock_data.data.externalColors[selectedExternalIndex];
  const {
    name: innerName,
    imagePath: innerImagePath,
    tags: innerTags,
  } = mock_data.data.innerColors[selectedInnerIndex];

  useEffect(() => {
    setExternalColorWord(externalImageUrl.split('exterior/')[1].slice(0, 3));
  }, [externalImageUrl]);

  useEffect(() => {
    setInnerColorWord(innerImagePath.split('interior/')[1].slice(0, 3));
  }, [innerImagePath]);

  function handleClickExternalColor(index: number) {
    handleSetExternalIndex(index)();
    setIsExternalPage(true);
  }

  function handleClickInnerColor(index: number) {
    handleSetInnerIndex(index)();
    setIsExternalPage(false);
  }

  function isSelectedExternalColor(name: string) {
    return externalName === name;
  }

  function isSelectedInnerColor(name: string) {
    return innerName === name;
  }

  return (
    <style.Container>
      <style.Wrapper>
        {isExternalPage ? (
          <>
            <ExternalCarImage color={externalColorWord} />
            <MyCarDescription title={externalName} price={extraFee} hasTag={true} tags={externalTags} />
          </>
        ) : (
          <>
            <InnerCarImage color={innerColorWord} />
            <MyCarDescription title={innerName} price={0} hasTag={true} tags={innerTags} />
          </>
        )}
      </style.Wrapper>
      <style.Wrapper>
        <style.Box>
          <style.TitleBox>
            <style.Title>외장 색상</style.Title>
            <style.ColorName>{externalName}</style.ColorName>
          </style.TitleBox>
          <style.Division />
          <style.ColorBox>
            {mock_data.data.externalColors.map(({ name, imageUrl, extraFee }, index) => (
              <style.ColorCard key={name} onClick={() => handleClickExternalColor(index)}>
                <style.ColorCardRect colorUrl={imageUrl} isActive={isSelectedExternalColor(name)} />
                <style.ColorCardName>{name}</style.ColorCardName>
                {extraFee !== 0 && <style.ColorCardName>(+{extraFee.toLocaleString()}원)</style.ColorCardName>}
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
            {mock_data.data.innerColors.map(({ name, imagePath }, index) => (
              <style.InteriorColorCard key={name} onClick={() => handleClickInnerColor(index)}>
                <style.InteriorColorButton isActive={isSelectedInnerColor(name)} bgImage={imagePath} />
                {isSelectedInnerColor(name) && <CheckIcon isInnerColorIcon={false} />}
              </style.InteriorColorCard>
            ))}
          </style.InteriorColorBox>
        </style.Box>
      </style.Wrapper>
    </style.Container>
  );
}
