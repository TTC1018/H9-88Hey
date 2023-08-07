import { useEffect, useState } from 'react';

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
  const [externalColor, setExternalColor] = useState('어비스 블랙 펄');
  const [colorWord, setColorWord] = useState('A2B');
  const [innerColorWord, setInnerColorWord] = useState('I49');
  const [innerColor, setInnerColor] = useState('퀼팅천연(블랙)');
  const [externalTags, setExternalTags] = useState<string[]>([
    '트렌디해요',
    '모두가 좋아하는 색상',
    '5개 데이터',
    '어비스 블랙 펄',
  ]);
  const [innerTags, setInnerTags] = useState<string[]>([]);

  useEffect(() => {
    const targetIndex = mock_data.data.externalColors.findIndex(color => color.name === externalColor);
    setColorWord(mock_data.data.externalColors[targetIndex].imageUrl.split('exterior/')[1].slice(0, 3));
  }, [externalColor]);

  useEffect(() => {
    const targetIndex = mock_data.data.innerColors.findIndex(color => color.name === innerColor);
    setInnerColorWord(mock_data.data.innerColors[targetIndex].imagePath.split('interior/')[1].slice(0, 3));
  }, [innerColor]);

  function handleClickExternalColor(name: string, tags: string[]) {
    setExternalColor(name);
    setExternalTags(tags);
    setIsExternalPage(true);
  }
  function handleClickInnerColor(name: string, tags: string[]) {
    setInnerColor(name);
    setInnerTags(tags);
    setIsExternalPage(false);
  }

  function isSelectedExternalColor(name: string) {
    return externalColor === name;
  }

  function isSelectedInnerColor(name: string) {
    return innerColor === name;
  }

  return (
    <style.Container>
      <style.Wrapper>
        {isExternalPage ? (
          <>
            <ExternalCarImage color={colorWord} />
            <MyCarDescription title={externalColor} price={0} hasTag={true} tags={externalTags} />
          </>
        ) : (
          <>
            <InnerCarImage color={innerColorWord} />
            <MyCarDescription title={innerColor} price={0} hasTag={true} tags={innerTags} />
          </>
        )}
      </style.Wrapper>
      <style.Wrapper>
        <style.Box>
          <style.TitleBox>
            <style.Title>외장 색상</style.Title>
            <style.ColorName>{externalColor}</style.ColorName>
          </style.TitleBox>
          <style.Division />
          <style.ColorBox>
            {mock_data.data.externalColors.map(color => (
              <style.ColorCard key={color.name} onClick={() => handleClickExternalColor(color.name, color.tags)}>
                <style.ColorCardRect colorUrl={color.imageUrl} isActive={isSelectedExternalColor(color.name)} />
                <style.ColorCardName>{color.name}</style.ColorCardName>
                {color.extraFee !== 0 && (
                  <style.ColorCardName>(+{color.extraFee.toLocaleString()}원)</style.ColorCardName>
                )}
                {isSelectedExternalColor(color.name) && <CheckIcon isInnerColorIcon={true} />}
              </style.ColorCard>
            ))}
          </style.ColorBox>
        </style.Box>
        <style.Box>
          <style.TitleBox>
            <style.Title>내장 색상</style.Title>
            <style.ColorName>{innerColor}</style.ColorName>
          </style.TitleBox>
          <style.Division />
          <style.InteriorColorBox>
            {mock_data.data.innerColors.map(color => (
              <style.InteriorColorCard key={color.name} onClick={() => handleClickInnerColor(color.name, color.tags)}>
                <style.InteriorColorButton isActive={isSelectedInnerColor(color.name)} bgImage={color.imagePath} />
                {isSelectedInnerColor(color.name) && <CheckIcon isInnerColorIcon={false} />}
              </style.InteriorColorCard>
            ))}
          </style.InteriorColorBox>
        </style.Box>
      </style.Wrapper>
    </style.Container>
  );
}
