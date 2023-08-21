import { Fragment, MouseEvent, useEffect, useRef, useState } from 'react';

import { useNavigate } from 'react-router-dom';

import { MyCarProps } from '@/types/trim';
import { MyChivingDataProps, MyChivingProps } from '@/types/myChiving';
import { useFetch } from '@/hooks/useFetch';
import { useModalContext } from '@/hooks/useModalContext';
import { ModalType } from '@/constants';

import { MyCarList } from '@/components/MyChiving/MyCarList';
import { NoDataInfo } from '@/components/MyChiving/NoDataInfo';
import { PopupModal } from '@/components/common/PopupModal';
import { ModalPortal } from '@/components/common/ModalPortal';

import * as Styled from './style';

type MatchPathType = Record<string, string>;

const matchPath: MatchPathType = {
  model: '/trim',
  trim: '/trim',
  engine: '/trim',
  bodyType: '/trim/engine',
  wheelDrive: '/trim/body-type',
  interiorColor: '/trim/wheel-drive',
  exteriorColor: '/trim/wheel-drive',
  selectedOptions: '/color',
};

const initialData = {
  nextOffset: 1,
  mychivings: [
    {
      myChivingId: 1,
      lastModifiedDate: '2023-07-19',
      isSaved: false,
      totalPrice: 0,
      model: {
        id: 1,
        name: '',
      },
      trim: {
        id: 1,
        name: '',
        price: 0,
      },
      engine: {
        id: 1,
        name: '',
        additionalPrice: 0,
      },
      bodyType: {
        id: 1,
        name: '',
        additionalPrice: 0,
      },
      wheelDrive: {
        id: 1,
        name: '',
        additionalPrice: 0,
      },
      interiorColor: {
        id: 1,
        name: '',
        colorImageUrl: '',
      },
      exteriorColor: {
        id: 1,
        name: '',
        carImageUrl: '',
        colorImageUrl: '',
        additionalPrice: 0,
      },
      selectedOptions: [
        {
          id: '',
          name: '',
          imageUrl: '',
          subOptions: [''],
          additionalPrice: 0,
        },
      ],
    },
  ],
};

interface ClickEventDataProps {
  deleteText: string;
  moveText: string;
}

export function MySavedCar() {
  const { handleOpen } = useModalContext();
  const navigate = useNavigate();

  const { data: myChivingData } = useFetch<MyChivingDataProps>({
    defaultValue: initialData,
    url: '/mychiving?limit=4&offset=0',
  });
  const [myChivings, setMyChivings] = useState<MyChivingProps[]>([]);

  const modalInfo = useRef({
    type: ModalType.CLOSE,
    contents: '',
    onClick: () => {},
  });

  const myCar: MyCarProps = {
    carType: { krName: '펠리세이드', enName: 'Palisade' },
    trim: { name: '', price: 0, id: 0 },
    engine: { name: '', additionalPrice: 0, id: 0 },
    bodyType: { name: '', additionalPrice: 0, id: 0 },
    wheelDrive: { name: '', additionalPrice: 0, id: 0 },
    exteriorColor: { name: '', colorImageUrl: '', additionalPrice: 0 },
    interiorColor: { name: '', colorImageUrl: '', id: 1 },
    options: [],
    carImageUrl: '',
  };

  function handleNavigate(myChiving: MyChivingProps) {
    const { trim, engine, bodyType, wheelDrive, exteriorColor, interiorColor, selectedOptions } = myChiving;
    const savedMyCar: MyCarProps = {
      ...myCar,
      trim: trim ?? myCar.trim,
      engine: engine ?? myCar.engine,
      bodyType: bodyType ?? myCar.bodyType,
      wheelDrive: wheelDrive ?? myCar.wheelDrive,
      exteriorColor: exteriorColor ?? myCar.exteriorColor,
      interiorColor: interiorColor ?? myCar.interiorColor,
      options: selectedOptions ? myCar.selectedOptions : [],
      carImageUrl: exteriorColor ? exteriorColor.carImageUrl : '',
    };

    localStorage.setItem('myCar', JSON.stringify(savedMyCar));
    if (myChiving.isSaved) {
      navigate('/result');
    } else {
      const targetIndex = Object.values(myChiving).findIndex(value => value === null);
      const lastIndex = Object.values(myChiving).length - 1;
      const targetPath = Object.keys(myChiving)[targetIndex === -1 ? lastIndex : targetIndex];

      navigate(matchPath[targetPath]);
    }
  }

  function handleDeleteList(myChiving: MyChivingProps) {
    const updatedMyChivings = myChivings.filter(({ myChivingId }) => myChivingId !== myChiving.myChivingId);
    setMyChivings(updatedMyChivings);
  }

  function handleClick(myChiving: MyChivingProps, data: ClickEventDataProps, event: MouseEvent<HTMLDivElement>) {
    const element = event.target as Element;
    const deleteButton = element.closest('button');

    if (deleteButton) {
      modalInfo.current = {
        type: ModalType.DELETE,
        contents: data.deleteText,
        onClick: () => handleDeleteList(myChiving),
      };
    } else {
      modalInfo.current = { type: ModalType.MOVE, contents: data.moveText, onClick: () => handleNavigate(myChiving) };
    }
    handleOpen();
  }

  useEffect(() => {
    setMyChivings(myChivingData.mychivings);
  }, [myChivingData]);

  return (
    <Fragment>
      <Styled.Contianer>
        {myChivings.length > 0 ? (
          <Styled.MyCarBox>
            {myChivings.map((data, index) => (
              <MyCarList key={index} myChiving={data} onClick={handleClick} />
            ))}
          </Styled.MyCarBox>
        ) : (
          <NoDataInfo infoText="내 차 목록에 저장한 차량이 없어요" buttonText="내 차 만들기" toPath="/trim" />
        )}
      </Styled.Contianer>
      <ModalPortal>
        <PopupModal
          type={modalInfo.current.type}
          onClick={modalInfo.current.onClick}
          contents={modalInfo.current.contents}
        />
      </ModalPortal>
    </Fragment>
  );
}
