import { useEffect, useState } from 'react';

import { useOutletContext } from 'react-router-dom';

import { MyCarLayoutContextType } from '@/types/trim';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { CheckIcon } from '@/components/common/CheckIcon';
import { ExternalCarImage } from '@/components/color/ExternalCarImage';
import { MyCarDescription } from '@/components/common/MyCarDescription';
import { InnerCarImage } from '@/components/color/InnerCarImage';

import * as style from './style';

const mockData = {
  data: {
    externalColors: [
      {
        name: '어비스 블랙 펄(1, 2)',
        imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/A2B/colorchip-exterior.png',
        availableInnerColor: [1, 2],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '어비스 블랙 펄'],
        extraFee: 0,
      },
      {
        name: '쉬머링 실버 메탈릭(2, 3)',
        imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/R2T/colorchip-exterior.png',
        availableInnerColor: [2, 3],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '쉬머링 실버 메탈릭'],
        extraFee: 0,
      },
      {
        name: '문라이트 블루 펄(3, 4)',
        imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/UB7/colorchip-exterior.png',
        availableInnerColor: [3, 4],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '문라이트 블루펄'],
        extraFee: 0,
      },
      {
        name: '가이아 브라운 펄(1, 4)',
        imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/D2S/colorchip-exterior.png',
        availableInnerColor: [1, 4],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '가이아 블아운 펄'],
        extraFee: 0,
      },
      {
        name: '그라파이트 그레이 메탈릭(2, 4)',
        imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/P7V/colorchip-exterior.png',
        availableInnerColor: [2, 4],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '그라파이트 그레이'],
        extraFee: 0,
      },
      {
        name: '크리미 화이트 펄(1)',
        imageUrl: 'https://www.hyundai.com/contents/vr360/LX06/exterior/WC9/colorchip-exterior.png',
        availableInnerColor: [1],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '크리미 화이트 펄'],
        extraFee: 100000,
      },
    ],
    innerColors: [
      {
        id: 1,
        name: '1',
        imagePath: 'https://www.hyundai.com/contents/vr360/LX06/interior/I49/colorchip-interior.png',
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '퀼팅천연'],
      },
      {
        id: 2,
        name: '2',
        imagePath: 'https://www.hyundai.com/contents/vr360/LX06/interior/I49/colorchip-interior.png',
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '퀼팅천연'],
      },
      {
        id: 3,
        name: '3',
        imagePath: 'https://www.hyundai.com/contents/vr360/LX06/interior/YJY/colorchip-interior.png',
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '쿨그레이'],
      },
      {
        id: 4,
        name: '4',
        imagePath: 'https://www.hyundai.com/contents/vr360/LX06/interior/I49/colorchip-interior.png',
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '퀼팅천연'],
      },
      {
        id: 5,
        name: '5',
        imagePath: 'https://www.hyundai.com/contents/vr360/LX06/interior/I49/colorchip-interior.png',
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터', '퀼팅천연'],
      },
      {
        id: 6,
        name: '6',
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
  } = mockData.data.externalColors[selectedExternalIndex];

  const availableInnerColorList = mockData.data.innerColors.filter(color => availableInnerColor.includes(color.id));
  const {
    id: innerId,
    name: innerName,
    imagePath: innerImagePath,
    tags: innerTags,
  } = availableInnerColorList[selectedInnerIndex];

  const {
    handleOuterColor,
    handleInnerColor,
    trim: { outerColor, innerColor },
  } = useOutletContext<MyCarLayoutContextType>();

  useEffect(() => {
    // 로컬 스트로지 변경 내용 유지
    const outerIndex = mockData.data.externalColors.findIndex(color => color.name === outerColor.title);
    let innerIndex = -1;

    console.log(outerIndex);

    // if (outerIndex !== -1) {
    //   innerIndex = mockData.data.externalColors[outerIndex].availableInnerColor.findIndex(
    //     colorId => colorId === innerColor.id
    //   );
    // }
    console.log(innerColor);
    outerIndex !== -1 && handleSetExternalIndex(outerIndex)();
    // innerIndex === -1
    // ? handleSetInnerIndex(innerIndex)()
    // handleInnerColor({
    //   color: availableInnerColorList[0].name,
    //   colorImage: availableInnerColorList[0].imagePath,
    //   id: availableInnerColorList[0].id,
    // });

    // local에 없을 때 용도 (위에 변화 없으면 위 변수로 사용 가능 할듯)
    if (outerColor.title === '') {
      handleOuterColor({
        color: mockData.data.externalColors[0].name,
        colorImage: mockData.data.externalColors[0].imageUrl,
        price: mockData.data.externalColors[0].extraFee,
      });
      handleInnerColor({
        color: availableInnerColorList[0].name,
        colorImage: availableInnerColorList[0].imagePath,
        id: availableInnerColorList[0].id,
      });
    }
  }, [availableInnerColorList]);

  useEffect(() => {
    setExternalColorWord(externalImageUrl.split('exterior/')[1].slice(0, 3));
    // 외장 색상 클릭 했을 때 내장 색상 첫번째 자동 선택
    outerColor.title === '' &&
      handleInnerColor({
        color: availableInnerColorList[0].name,
        colorImage: availableInnerColorList[0].imagePath,
        id: availableInnerColorList[0].id,
      });
  }, [externalImageUrl]);

  useEffect(() => {
    setInnerColorWord(innerImagePath.split('interior/')[1].slice(0, 3));
  }, [innerImagePath]);

  function handleClickExternalColor(index: number) {
    handleSetExternalIndex(index)();
    handleSetInnerIndex(0)(); // 두번째 내장 선택에서 하나만 있는 경우
    handleOuterColor({
      color: mockData.data.externalColors[index].name,
      colorImage: mockData.data.externalColors[index].imageUrl,
      price: mockData.data.externalColors[index].extraFee,
    });
    setIsExternalPage(true);
  }

  // Good
  function handleClickInnerColor(index: number) {
    handleSetInnerIndex(index)();
    handleInnerColor({
      color: availableInnerColorList[index].name,
      colorImage: availableInnerColorList[index].imagePath,
      id: availableInnerColorList[index].id,
    });
    setIsExternalPage(false);
  }

  function isSelectedExternalColor(name: string) {
    return externalName === name;
  }

  function isSelectedInnerColor(name: string) {
    return innerName === name;
  }

  const descriptionTitle = isExternalPage ? externalName : innerName;
  const descriptionPrice = isExternalPage ? extraFee : 0;
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
            {mockData.data.externalColors.map(({ name, imageUrl, extraFee }, index) => (
              <style.ColorCard key={name} onClick={() => handleClickExternalColor(index)}>
                <style.ColorCardRect colorUrl={imageUrl} isActive={isSelectedExternalColor(name)} />
                <style.ColorCardName>{name}</style.ColorCardName>
                {extraFee > 0 && <style.ColorCardName>(+{extraFee.toLocaleString()}원)</style.ColorCardName>}
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
            {availableInnerColorList.map(({ name, imagePath }, index) => (
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
