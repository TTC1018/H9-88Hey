import { Fragment, MouseEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';

import { ModalType } from '@/constants';

import { MyChivingDataProps } from '@/types/myChiving';
import { useFetch } from '@/hooks/useFetch';
import { useModalContext } from '@/hooks/useModalContext';

import { MyCarList } from '@/components/MyChiving/MyCarList';
import { NoDataInfo } from '@/components/MyChiving/NoDataInfo';
import { PopupModal } from '@/components/common/PopupModal';
import { ModalPortal } from '@/components/common/ModalPortal';

import * as Styled from './style';

const savedInitialData = {
  myarchivings: [
    {
      id: 111,
      model: '',
      trim: '',
      isSaved: true,
      trimOptions: [''],
      lastModifiedDate: '',
      selectedOptions: [
        {
          name: '',
          imageUrl: '',
        },
      ],
    },
  ],
};

const tempInitialData = {
  myarchivings: [
    {
      id: 222,
      model: '',
      trim: '',
      isSaved: false,
      trimOptions: [''],
      lastModifiedDate: '',
      selectedOptions: [
        {
          name: '',
          imageUrl: '',
        },
      ],
    },
  ],
};

interface Props {
  type: ModalType;
  contents?: string;
  onClick: () => void;
}

interface ClickEventDataProps {
  deleteText: string;
  moveText: string;
}

export function MySavedCar() {
  const { handleOpen } = useModalContext();
  const navigate = useNavigate();

  const { data: tempData } = useFetch<MyChivingDataProps>({ defaultValue: tempInitialData, url: '/mychiving/temp' });
  const { data: savedData } = useFetch<MyChivingDataProps>({ defaultValue: savedInitialData, url: '/mychiving' });
  const allData = [...tempData.myarchivings, ...savedData.myarchivings];

  const [modalInfo, setModalInfo] = useState<Props>({
    type: ModalType.CLOSE,
    contents: '',
    onClick: () => {},
  });

  function handleNavigate() {
    navigate('/trim');
  }

  // 목록 제거 함수
  function handleDeleteList() {}

  function handleClick(data: ClickEventDataProps, event: MouseEvent<HTMLDivElement>) {
    const el = event.target as Element;
    const deleteButton = el.closest('button');

    if (deleteButton) {
      setModalInfo({ type: ModalType.DELETE, contents: data.deleteText, onClick: handleDeleteList });
    } else {
      setModalInfo({ type: ModalType.MOVE, contents: data.moveText, onClick: handleNavigate });
    }
    handleOpen();
  }

  return (
    <Fragment>
      <Styled.Contianer>
        {allData.length > 0 ? (
          <Styled.MyCarBox>
            {allData.map((data, index) => (
              <MyCarList
                key={index}
                isSaved={data.isSaved}
                model={data.model}
                trim={data.trim}
                trimOptions={data.trimOptions}
                lastModifiedDate={data.lastModifiedDate}
                selectedOptions={data.selectedOptions}
                onClick={handleClick}
              />
            ))}
          </Styled.MyCarBox>
        ) : (
          <NoDataInfo infoText="내 차 목록에 저장한 차량이 없어요" buttonText="내 차 만들기" toPath="/trim" />
        )}
      </Styled.Contianer>
      <ModalPortal>
        <PopupModal type={modalInfo.type} onClick={modalInfo.onClick} contents={modalInfo.contents} />
      </ModalPortal>
    </Fragment>
  );
}
