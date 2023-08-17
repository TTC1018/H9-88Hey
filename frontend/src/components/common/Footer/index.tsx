import { MutableRefObject, useState } from 'react';

import { useLocation, useNavigate } from 'react-router-dom';

import { checkIsOption } from '@/utils';
import { MyCarProps } from '@/types/trim';
import { NAVIGATION_PATH, TAG_CHIP_MAX_NUMBER } from '@/constants';

import { EstimateModal } from './EstimateModal';

import * as Styled from './style';

interface FooterProps {
  myCarData: MyCarProps;
  totalPrice: number;
  carCode: MutableRefObject<string>;
  onSetLocalStorage: () => void;
}

export function Footer({ myCarData, totalPrice, carCode, onSetLocalStorage }: FooterProps) {
  const { pathname } = useLocation();
  const navigate = useNavigate();

  const [isOpen, setIsOpen] = useState(false);

  function handleOpenModal() {
    setIsOpen(true);
  }
  function handleCloseModal() {
    setIsOpen(false);
  }

  const { trim, engine, bodyType, wheelDrive, outerColor, innerColor, options } = myCarData;

  const trimOptions = `${engine.title}${bodyType.title !== '' ? '/' : ''}${bodyType.title}${
    wheelDrive.title !== '' ? '/' : ''
  }${wheelDrive.title}`;

  const pathKey = pathname.endsWith('/') ? pathname.slice(0, -1) : pathname;

  function handleNavigate(path: string) {
    if (path === '') {
      return;
    }

    onSetLocalStorage();

    if (checkIsOption(path)) {
      navigate({
        pathname: path,
        search: `?car_code=${carCode.current}`,
      });

      return;
    }

    navigate(path);
  }

  function handleNextNavigate() {
    const path = NAVIGATION_PATH[pathKey as keyof typeof NAVIGATION_PATH].next;
    handleNavigate(path);
  }
  function handlePrevNavigate() {
    const path = NAVIGATION_PATH[pathKey as keyof typeof NAVIGATION_PATH].prev;
    handleNavigate(path);
  }

  if (pathname === '/result') {
    return null;
  }

  return (
    <Styled.Container>
      <Styled.TrimWrapper>
        <Styled.Title>트림</Styled.Title>
        <Styled.CarName>{trim.title}</Styled.CarName>
        <Styled.TrimDetail>{trimOptions}</Styled.TrimDetail>
      </Styled.TrimWrapper>
      <Styled.Division />
      <Styled.ColorWrapper>
        <Styled.Title>선택 색상</Styled.Title>
        <Styled.ColorBox>
          <Styled.ColorTitle>외장</Styled.ColorTitle>
          <Styled.ColorName>
            <Styled.ColorCircle imageUrl={outerColor.imageUrl || ''} />
            <Styled.ColorNameText>{outerColor.title}</Styled.ColorNameText>
          </Styled.ColorName>
        </Styled.ColorBox>
        <Styled.ColorBox>
          <Styled.ColorTitle>내장</Styled.ColorTitle>
          <Styled.ColorName>
            <Styled.ColorCircle imageUrl={innerColor.imageUrl || ''} />
            <Styled.ColorNameText>{innerColor.title}</Styled.ColorNameText>
          </Styled.ColorName>
        </Styled.ColorBox>
      </Styled.ColorWrapper>
      <Styled.Division />
      <Styled.OptionWrapper>
        <Styled.Title>선택 옵션</Styled.Title>
        <Styled.OptionBox onClick={handleOpenModal}>
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
      {isOpen && <EstimateModal onClick={handleCloseModal} myCarData={myCarData} totalPrice={totalPrice} />}
    </Styled.Container>
  );
}
