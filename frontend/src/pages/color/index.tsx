import { useState } from 'react';

import { CheckIcon } from '@/components/common/CheckIcon';

import * as style from './style';

const external_colors = [
  {
    name: '어비스 블랙 펄',
    rgb: '#141414',
  },
  {
    name: '쉬머링 실버 메탈릭',
    rgb: '#8E918F',
  },
  {
    name: '문라이트 블루 펄',
    rgb: '#121926',
  },
  {
    name: '가이아 브라운 펄',
    rgb: '#282520',
  },
  {
    name: '그라파이트 그레이 메탈릭',
    rgb: '#323534',
  },
  {
    name: '크리미 화이트 펄',
    rgb: '#F0F2F1',
  },
];
const inner_colors = [
  {
    name: '내부 색상 1',
    url: 'src/assets/InteriorColor.png',
  },
  {
    name: '내부 색상 2',
    url: 'src/assets/InteriorColor2.png',
  },
];

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
        <style.TitleBox>
          <style.Title>외장 색상</style.Title>
          <style.ColorName>{externalColor}</style.ColorName>
        </style.TitleBox>
        <style.Division />
        <style.ColorBox>
          {external_colors.map(color => (
            <style.ColorCard key={color.name} onClick={() => handleClickExternalColor(color.name)}>
              <style.ColorCardRect colorValue={color.rgb} isActive={isSelectedExternalColor(color.name)} />
              <style.ColorCardName>{color.name}</style.ColorCardName>
              {isSelectedExternalColor(color.name) && <CheckIcon isInnerColorIcon={true} />}
            </style.ColorCard>
          ))}
        </style.ColorBox>
      </style.Wrapper>
      <style.Wrapper>
        <style.TitleBox>
          <style.Title>내장 색상</style.Title>
          <style.ColorName>{innerColor}</style.ColorName>
        </style.TitleBox>
        <style.Division />
        <style.InteriorColorBox>
          {inner_colors.map(color => (
            <style.InteriorColorCard key={color.name} onClick={() => handleClickInnerColor(color.name)}>
              <style.InteriorColorButton isActive={isSelectedInnerColor(color.name)} bgImage={color.url} />
              {isSelectedInnerColor(color.name) && <CheckIcon isInnerColorIcon={false} />}
            </style.InteriorColorCard>
          ))}
        </style.InteriorColorBox>
      </style.Wrapper>
    </style.Container>
  );
}
