import { useState } from 'react';

import { CheckIcon } from '@/components/common/CheckIcon';
import { CarImage } from '@/components/color/CarImage';
import { MyCarDescription } from '@/components/common/MyCarDescription';

import * as style from './style';

const mock_data = {
  data: {
    external_colors: [
      {
        name: '어비스 블랙 펄',
        rgb_value: '#141414',
        available_inner_color: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
      {
        name: '쉬머링 실버 메탈릭',
        rgb_value: '#8E918F',
        available_inner_color: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
      {
        name: '문라이트 블루 펄',
        rgb_value: '#121926',
        available_inner_color: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
      {
        name: '가이아 브라운 펄',
        rgb_value: '#282520',
        available_inner_color: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
      {
        name: '그라파이트 그레이 메탈릭',
        rgb_value: '#323534',
        available_inner_color: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
      {
        name: '크리미 화이트 펄',
        rgb_value: '#F0F2F1',
        available_inner_color: ['퀄팅 천연', '퀄팅 그레이 샤넬'],
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
    ],
    inner_colors: [
      {
        name: '내부 색상 1',
        image_path: 'src/assets/InteriorColor.png',
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
      {
        name: '내부 색상 2',
        image_path: 'src/assets/InteriorColor2.png',
        tags: ['트렌디해요', '모두가 좋아하는 색상', '5개 데이터'],
      },
    ],
  },
};

export function Color() {
  const [externalColor, setExternalColor] = useState('');
  const [innerColor, setInnerColor] = useState('');
  function handleClickExternalColor(name: string) {
    setExternalColor(name);
  }
  function handleClickInnerColor(name: string) {
    setInnerColor(name);
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
        <CarImage />
        <MyCarDescription title={externalColor} price={0} hasTag={false} />
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
                <style.ColorCardRect colorValue={color.rgb_value} isActive={isSelectedExternalColor(color.name)} />
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
