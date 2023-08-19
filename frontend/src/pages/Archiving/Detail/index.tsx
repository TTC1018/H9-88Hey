import { Fragment } from 'react';

import { useLocation, useNavigate } from 'react-router-dom';

import { ModalType } from '@/constants';
import { ArchivingProps } from '@/types/archiving';
import { combineWithSlash, formatDate } from '@/utils';
import { useModalContext } from '@/hooks/useModalContext';

import { PopupModal } from '@/components/common/PopupModal';
import { ModalPortal } from '@/components/common/ModalPortal';
import { OptionList } from '@/components/Archiving/OptionList';
import { DetailHeader } from '@/components/Archiving/DetailHeader';
import { DetailDescription } from '@/components/Archiving/DetailDescription';

import * as Styled from './style';

export function Detail() {
  const location = useLocation();
  const navigate = useNavigate();

  const {
    modelName,
    trim,
    creationDate,
    engine,
    bodyType,
    wheelDrive,
    exteriorColor,
    interiorColor,
    review,
    isPurchase,
    totalPrice,
    selectedOptions,
  }: ArchivingProps = {
    ...location.state,
  };

  const options = selectedOptions.map(option => option.name);
  const dateText = `에 ${isPurchase ? '구매' : '시승'}했어요`;

  const myCar = {
    carType: { krName: '펠리세이드', enName: 'Palisade' },
    trim: { ...trim },
    engine: { ...engine },
    bodyType: { ...bodyType },
    wheelDrive: { ...wheelDrive },
    exteriorColor: {
      ...exteriorColor,
    },
    interiorColor: { ...interiorColor },
    options: selectedOptions.map(props => {
      return {
        ...props,
      };
    }),
    carImageUrl: exteriorColor.carImageUrl,
  };

  const { handleOpen } = useModalContext();
  function handleNavigate() {
    localStorage.setItem('myCar', JSON.stringify(myCar));
    navigate('/trim');
  }

  return (
    <Fragment>
      <Styled.Container>
        <Styled.HeaderWrapper>
          <DetailHeader
            title={`${modelName} ${trim.name}`}
            date={formatDate(creationDate) + dateText}
            trimOptions={combineWithSlash([engine.name, bodyType.name, wheelDrive.name])}
            exteriorColor={exteriorColor.name}
            interiorColor={interiorColor.name}
            review={review}
            imageUrl={`${exteriorColor.carImageUrl}001.png`}
          />
        </Styled.HeaderWrapper>
        <Styled.DescriptionWrapper>
          <DetailDescription
            totalPrice={totalPrice}
            options={options}
            onClickSaveButton={() => {}}
            onClickStartButton={handleOpen}
          />
        </Styled.DescriptionWrapper>
        <Styled.Line />
        <Styled.OptionWrapper>
          <OptionList options={selectedOptions} />
        </Styled.OptionWrapper>
      </Styled.Container>
      <ModalPortal>
        <PopupModal type={ModalType.MOVE} contents={formatDate(creationDate)} onClick={handleNavigate} />
      </ModalPortal>
    </Fragment>
  );
}
