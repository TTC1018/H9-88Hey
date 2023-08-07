import { useEffect, useState } from 'react';

import { CheckIcon } from '@/components/common/CheckIcon';
import { ExternalCarImage } from '@/components/color/ExternalCarImage';
import { MyCarDescription } from '@/components/common/MyCarDescription';
import { InnerCarImage } from '@/components/color/InnerCarImage';

import * as style from './style';

const mock_data = {
  data: {
    external_colors: [
      {
        name: '어비스 블랙 펄',
        image_url: 'https://www.hyundai.com/contents/vr360/LX06/exterior/A2B/colorchip-exterior.png',
        available_inner_color: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
      {
        name: '쉬머링 실버 메탈릭',
        image_url: 'https://www.hyundai.com/contents/vr360/LX06/exterior/R2T/colorchip-exterior.png',
        available_inner_color: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
      {
        name: '문라이트 블루 펄',
        image_url: 'https://www.hyundai.com/contents/vr360/LX06/exterior/UB7/colorchip-exterior.png',
        available_inner_color: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
      {
        name: '가이아 브라운 펄',
        image_url: 'https://www.hyundai.com/contents/vr360/LX06/exterior/D2S/colorchip-exterior.png',
        available_inner_color: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
      {
        name: '그라파이트 그레이 메탈릭',
        image_url: 'https://www.hyundai.com/contents/vr360/LX06/exterior/P7V/colorchip-exterior.png',
        available_inner_color: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
      {
        name: '크리미 화이트 펄',
        image_url: 'https://www.hyundai.com/contents/vr360/LX06/exterior/WC9/colorchip-exterior.png',
        available_inner_color: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
    ],
    inner_colors: [
      {
        name: '퀼팅천연(블랙)',
        image_path: 'https://www.hyundai.com/contents/vr360/LX06/interior/I49/colorchip-interior.png',
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
      {
        name: '쿨그레이',
        image_path: 'https://www.hyundai.com/contents/vr360/LX06/interior/YJY/colorchip-interior.png',
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
    ],
  },
};

export function Color() {
  const [externalColor, setExternalColor] = useState('어비스 블랙 펄');
  const [colorWord, setColorWord] = useState('A2B');
  const [innerColorWord, setInnerColorWord] = useState('A2B');
  const [innerColor, setInnerColor] = useState('퀼팅천연(블랙)');
  const [isExternalPage, setIsExternalPage] = useState(true);

  useEffect(() => {
    const targetIndex = mock_data.data.external_colors.findIndex(color => color.name === externalColor);
    setColorWord(mock_data.data.external_colors[targetIndex].image_url.split('exterior/')[1].slice(0, 3));
  }, [externalColor]);

  useEffect(() => {
    const targetIndex = mock_data.data.inner_colors.findIndex(color => color.name === innerColor);
    setInnerColorWord(mock_data.data.inner_colors[targetIndex].image_path.split('interior/')[1].slice(0, 3));
  }, [innerColor]);

  function handleClickExternalColor(name: string) {
    setExternalColor(name);
    setIsExternalPage(true);
  }
  function handleClickInnerColor(name: string) {
    setInnerColor(name);
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
            <MyCarDescription title={externalColor} price={0} hasTag={false} />
          </>
        ) : (
          <>
            <InnerCarImage color={innerColorWord} />
            <MyCarDescription title={innerColor} price={0} hasTag={false} />
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
            {mock_data.data.external_colors.map(color => (
              <style.ColorCard key={color.name} onClick={() => handleClickExternalColor(color.name)}>
                <style.ColorCardRect colorUrl={color.image_url} isActive={isSelectedExternalColor(color.name)} />
                <style.ColorCardName>{color.name}</style.ColorCardName>
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
            {mock_data.data.inner_colors.map(color => (
              <style.InteriorColorCard key={color.name} onClick={() => handleClickInnerColor(color.name)}>
                <style.InteriorColorButton isActive={isSelectedInnerColor(color.name)} bgImage={color.image_path} />
                {isSelectedInnerColor(color.name) && <CheckIcon isInnerColorIcon={false} />}
              </style.InteriorColorCard>
            ))}
          </style.InteriorColorBox>
        </style.Box>
      </style.Wrapper>
    </style.Container>
  );
}
