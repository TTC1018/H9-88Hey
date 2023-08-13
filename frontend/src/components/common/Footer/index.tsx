import { useLocation, useNavigate } from 'react-router-dom';

import { MyCarType } from '@/types/trim';
import { TAG_CHIP_MAX_NUMBER } from '@/constants';

import * as Styled from './style';

const NAVIGATION_PATH = {
  '/trim': { prev: '', next: '/trim/engine' },
  '/trim/engine': { prev: '/trim', next: '/trim/body-type' },
  '/trim/body-type': { prev: '/trim/engine', next: '/trim/wheel-drive' },
  '/trim/wheel-drive': { prev: '/trim/body-type', next: 'color' },
  '/color': { prev: '/trim/wheel-drive', next: '/option' },
  '/option': { prev: '/color', next: '/option/h-genuine-accessories' },
  '/option/h-genuine-accessories': { prev: '/option', next: '/option/n-performance' },
  '/option/n-performance': { prev: '/option/h-genuine-accessories', next: '/result' },
  '/result': { prev: '/option/n-performance', next: '' },
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

  if (pathname === '/result') {
    return null;
  }

  return (
    <Styled.Container>
      <Styled.TrimWrapper>
        <Styled.Title>트림</Styled.Title>
        <Styled.CarName>{model.title}</Styled.CarName>
        <Styled.TrimDetail>{trim}</Styled.TrimDetail>
      </Styled.TrimWrapper>
      <Styled.Division />
      <Styled.ColorWrapper>
        <Styled.Title>선택 색상</Styled.Title>
        <Styled.ColorBox>
          <Styled.ColorTitle>외장</Styled.ColorTitle>
          <Styled.ColorName>
            <Styled.ColorCircle />
            <Styled.ColorNameText>{color.inner[1]}</Styled.ColorNameText>
          </Styled.ColorName>
        </Styled.ColorBox>
        <Styled.ColorBox>
          <Styled.ColorTitle>내장</Styled.ColorTitle>
          <Styled.ColorName>
            <Styled.ColorCircle />
            <Styled.ColorNameText>{color.outer[1]}</Styled.ColorNameText>
          </Styled.ColorName>
        </Styled.ColorBox>
      </Styled.ColorWrapper>
      <Styled.Division />
      <Styled.OptionWrapper>
        <Styled.Title>선택 옵션</Styled.Title>
        <Styled.OptionBox>
          {options.slice(0, TAG_CHIP_MAX_NUMBER).map(({ name }) => (
            <Styled.Option key={name}>{name}</Styled.Option>
          ))}
          {options.length > TAG_CHIP_MAX_NUMBER && (
            <Styled.Option>+{options.length - TAG_CHIP_MAX_NUMBER}</Styled.Option>
          )}
        </Styled.OptionBox>
      </Styled.OptionWrapper>
      <Styled.Division />
      <Styled.PriceWrapper>
        <Styled.Title>예상 가격</Styled.Title>
        <Styled.PriceText>
          {totalPrice.toLocaleString()}
          <Styled.PriceUnitText>원</Styled.PriceUnitText>
        </Styled.PriceText>
      </Styled.PriceWrapper>
      <Styled.ButtonWrapper>
        <Styled.PrevButton onClick={handlePrevNavigate}>이전</Styled.PrevButton>
        <Styled.NextButton onClick={handleNextNavigate}>다음</Styled.NextButton>
      </Styled.ButtonWrapper>
    </Styled.Container>
  );
}
