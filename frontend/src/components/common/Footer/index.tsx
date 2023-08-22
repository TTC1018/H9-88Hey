import { Dispatch, MutableRefObject, useRef, useState } from 'react';

import { useLocation, useNavigate } from 'react-router-dom';

import { checkIsOptionPage, checkIsHGenuineAccessoriesPage, getLocalStorage, combineWithSlash } from '@/utils';
import { ActionType, MyCarProps } from '@/types/trim';
import { useCountPrice } from '@/hooks/useCountPrice';
import { MyCarActionType, NAVIGATION_PATH, TAG_CHIP_MAX_NUMBER } from '@/constants';

import { ColorCircle } from '@/components/common/ColorCircle';
import { EstimateModal } from './EstimateModal';

import * as Styled from './style';

interface FooterProps {
  myCarData: MyCarProps;
  calculatePrice: number;
  carCode: MutableRefObject<string>;
  setDisplayAutoSaving: () => void;
  dispatch: Dispatch<ActionType>;
}

export function Footer({ myCarData, calculatePrice, carCode, setDisplayAutoSaving, dispatch }: FooterProps) {
  const prevPrice = useRef(calculatePrice);
  const totalPrice = useCountPrice({
    prevPrice: prevPrice.current,
    nextPrice: calculatePrice,
  });
  prevPrice.current = totalPrice;

  const { pathname } = useLocation();
  const navigate = useNavigate();

  const [isOpen, setIsOpen] = useState(false);
  function handleOpenModal() {
    setIsOpen(true);
  }
  function handleCloseModal() {
    setIsOpen(false);
  }

  function handleLocalStorage() {
    localStorage.setItem('myCar', JSON.stringify(myCarData));
  }

  const { trim, engine, bodyType, wheelDrive, exteriorColor, interiorColor, options } = myCarData;
  const trimOptions = combineWithSlash([engine.name, bodyType.name, wheelDrive.name]);

  const pathKey = pathname.endsWith('/') ? pathname.slice(0, -1) : pathname;
  function handleNavigate(path: string) {
    if (path === '') {
      return;
    }

    let optionQuery = '';

    if (checkIsHGenuineAccessoriesPage(path)) {
      options.forEach(({ id, path }) => {
        if (path !== '/option') {
          return;
        }

        optionQuery += `&select_option=${id}`;
      });
    }

    handleLocalStorage();

    if (checkIsOptionPage(path)) {
      const currentCarCode = getLocalStorage('carCode');
      if (currentCarCode !== carCode.current) {
        dispatch({ type: MyCarActionType.CLEAR_OPTION, payload: [] });
      }

      localStorage.setItem('carCode', carCode.current);
      const carCodeQuery = `?car_code=${carCode.current}`;
      navigate({
        pathname: path,
        search: `${carCodeQuery}${optionQuery}`,
      });
      return;
    }

    navigate(path);
  }

  function handleNextNavigate() {
    const path = NAVIGATION_PATH[pathKey as keyof typeof NAVIGATION_PATH].next;
    handleNavigate(path);
    setDisplayAutoSaving();
  }

  function handlePrevNavigate() {
    const path = NAVIGATION_PATH[pathKey as keyof typeof NAVIGATION_PATH].prev;
    handleNavigate(path);
    setDisplayAutoSaving();
  }

  if (pathname === '/result') {
    return null;
  }

  return (
    <Styled.Container>
      <Styled.TrimWrapper>
        <Styled.Title>트림</Styled.Title>
        <Styled.CarName>{trim.name}</Styled.CarName>
        <Styled.TrimDetail>{trimOptions}</Styled.TrimDetail>
      </Styled.TrimWrapper>
      <Styled.Division />
      <Styled.ColorWrapper>
        <Styled.Title>선택 색상</Styled.Title>
        <Styled.ColorBox>
          <Styled.ColorTitle>외장</Styled.ColorTitle>
          <Styled.ColorName>
            <ColorCircle imageUrl={exteriorColor.colorImageUrl || ''} />
            <Styled.ColorNameText>{exteriorColor.name}</Styled.ColorNameText>
          </Styled.ColorName>
        </Styled.ColorBox>
        <Styled.ColorBox>
          <Styled.ColorTitle>내장</Styled.ColorTitle>
          <Styled.ColorName>
            <ColorCircle imageUrl={interiorColor.colorImageUrl || ''} />
            <Styled.ColorNameText>{interiorColor.name}</Styled.ColorNameText>
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
