import { Dispatch, MutableRefObject, useEffect, useRef, useState } from 'react';

import { useLocation, useNavigate } from 'react-router-dom';

import { useCountPrice } from '@/hooks/useCountPrice';
import { useAuthMutation } from '@/hooks/useAuthMutation';
import { SaveDataProps } from '@/types/myChiving';
import { ActionType, MyCarProps, SaveProps, TempSaveProps } from '@/types/trim';
import { MyCarActionType, NAVIGATION_PATH, TAG_CHIP_MAX_NUMBER } from '@/constants';
import { checkIsOptionPage, checkIsHGenuineAccessoriesPage, getLocalStorage, combineWithSlash } from '@/utils';

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

  const { data, authMutation } = useAuthMutation<SaveDataProps, TempSaveProps>({ url: '/mychiving/temp' });
  const { authMutation: saveMychiving } = useAuthMutation<SaveDataProps, SaveProps>({ url: '/mychiving' });

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

  function handleTempSave() {
    const myChivingId = localStorage.getItem('myChivingId');
    const saveData = {
      myChivingId: myChivingId ? myChivingId : null,
      trim: trim.id === 0 ? null : trim.id,
      engine: engine.id === 0 ? null : engine.id,
      wheelType: wheelDrive.id === 0 ? null : wheelDrive.id,
      bodyType: bodyType.id === 0 ? null : bodyType.id,
      exteriorColor: exteriorColor.id === 0 ? null : exteriorColor.id,
      interiorColor: interiorColor.id === 0 ? null : interiorColor.id,
      selectOptions: options.map(({ id }) => id).length === 0 ? null : options.map(({ id }) => id),
    };
    authMutation({ method: 'POST', data: saveData });
  }

  function handleSave() {
    const myChivingId = localStorage.getItem('myChivingId');
    const saveData = {
      myChivingId: myChivingId ?? '',
      trim: trim.id,
      engine: engine.id,
      wheelType: wheelDrive.id,
      bodyType: bodyType.id,
      exteriorColor: exteriorColor.id,
      interiorColor: interiorColor.id,
      selectOptions: options.map(({ id }) => id),
    };
    saveMychiving({ method: 'POST', data: saveData });

    navigate('/result', {
      state: myCarData,
    });
    localStorage.removeItem('myChivingId');
    localStorage.removeItem('myCar');
    localStorage.removeItem('carCode');
  }

  function handleNextNavigate() {
    const path = NAVIGATION_PATH[pathKey as keyof typeof NAVIGATION_PATH].next;

    if (path === '/result') {
      handleSave();
    } else {
      handleTempSave();
      handleNavigate(path);
      setDisplayAutoSaving();
    }
  }

  function handlePrevNavigate() {
    const path = NAVIGATION_PATH[pathKey as keyof typeof NAVIGATION_PATH].prev;
    handleNavigate(path);
    setDisplayAutoSaving();
  }

  useEffect(() => {
    if (data) {
      localStorage.setItem('myChivingId', data.myChivingId);
    }
  }, [data]);
  const noFooterPaths = ['/', '/signup', '/result'];

  if (noFooterPaths.includes(pathname)) {
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
        <Styled.TitleBox>
          <Styled.Title>선택 옵션</Styled.Title>
          <Styled.TitleButton onClick={handleOpenModal}>견적 요약보기</Styled.TitleButton>
        </Styled.TitleBox>
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
      {isOpen && <EstimateModal onClick={handleCloseModal} myCarData={myCarData} totalPrice={totalPrice} />}
    </Styled.Container>
  );
}
