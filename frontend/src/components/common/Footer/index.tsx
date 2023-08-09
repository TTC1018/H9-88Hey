import { useLocation, useNavigate } from 'react-router-dom';

import { MyCarType } from '@/types/trim';
import { TAG_CHIP_MAX_NUMBER } from '@/constants';

import * as style from './style';

const NAVIGATION_PATH = {
  '/trim': { prev: '', next: '/trim/engine' },
  '/trim/engine': { prev: '/trim', next: '/trim/body-type' },
  '/trim/body-type': { prev: '/trim/engine', next: '/trim/wheel-drive' },
  '/trim/wheel-drive': { prev: '/trim/body-type', next: 'color' },
  '/color': { prev: '/trim/wheel-drive', next: '/option' },
  '/option': { prev: '/color', next: '/option/h-genuine-accessories' },
  '/option/h-genuine-accessories': { prev: '/option', next: '/option/n-performance' },
  '/option/n-performance': { prev: '/option/h-genuine-accessories', next: '' },
};

interface FooterProps {
  options: MyCarType;
  totalPrice: number;
  onSetLocalStorage: () => void;
}
export function Footer({
  options: { model, engine, bodyType, wheelDrive, color, options },
  totalPrice,
  onSetLocalStorage,
}: FooterProps) {
  const { pathname } = useLocation();
  const navigate = useNavigate();

  const trim = `${engine.title}${bodyType.title !== '' ? '/' : ''}${bodyType.title}${
    wheelDrive.title !== '' ? '/' : ''
  }${wheelDrive.title}`;

  function handleNextNavigate() {
    const path = NAVIGATION_PATH[pathname as keyof typeof NAVIGATION_PATH].next;
    if (path !== '') {
      onSetLocalStorage();
      navigate(path);
    }
  }
  function handlePrevNavigate() {
    const path = NAVIGATION_PATH[pathname as keyof typeof NAVIGATION_PATH].prev;
    path !== '' && navigate(path);
  }

  return (
    <style.Container>
      <style.TrimWrapper>
        <style.Title>트림</style.Title>
        <style.CarName>{model.title}</style.CarName>
        <style.TrimDetail>{trim}</style.TrimDetail>
      </style.TrimWrapper>
      <style.Division />
      <style.ColorWrapper>
        <style.Title>선택 색상</style.Title>
        <style.ColorBox>
          <style.ColorTitle>외장</style.ColorTitle>
          <style.ColorName>
            <style.ColorCircle />
            <style.ColorNameText>{color.inner[1]}</style.ColorNameText>
          </style.ColorName>
        </style.ColorBox>
        <style.ColorBox>
          <style.ColorTitle>내장</style.ColorTitle>
          <style.ColorName>
            <style.ColorCircle />
            <style.ColorNameText>{color.outer[1]}</style.ColorNameText>
          </style.ColorName>
        </style.ColorBox>
      </style.ColorWrapper>
      <style.Division />
      <style.OptionWrapper>
        <style.Title>선택 옵션</style.Title>
        <style.OptionBox>
          {options.slice(0, TAG_CHIP_MAX_NUMBER).map(({ name }) => (
            <style.Option>{name}</style.Option>
          ))}
          {options.length > TAG_CHIP_MAX_NUMBER && <style.Option>+{options.length - TAG_CHIP_MAX_NUMBER}</style.Option>}
        </style.OptionBox>
      </style.OptionWrapper>
      <style.Division />
      <style.PriceWrapper>
        <style.Title>예상 가격</style.Title>
        <style.PriceText>
          {totalPrice.toLocaleString()}
          <style.PriceUnitText>원</style.PriceUnitText>
        </style.PriceText>
      </style.PriceWrapper>
      <style.ButtonWrapper>
        <style.PrevButton onClick={handlePrevNavigate}>이전</style.PrevButton>
        <style.NextButton onClick={handleNextNavigate}>다음</style.NextButton>
      </style.ButtonWrapper>
    </style.Container>
  );
}
