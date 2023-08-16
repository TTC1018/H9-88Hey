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
    model,
    trim,
    creationDate,
    trimOptions,
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

  const { handleOpen } = useModalContext();
  function handleNavigate() {
    navigate('/trim');
  }

  return (
    <Fragment>
      <Styled.Container>
        <Styled.HeaderWrapper>
          <DetailHeader
            title={`${model} ${trim}`}
            date={formatDate(creationDate) + dateText}
            trimOptions={combineWithSlash(trimOptions)}
            exteriorColor={exteriorColor}
            interiorColor={interiorColor}
            review={review}
            imageUrl="https://www.hyundai.com/contents/vr360/LX06/exterior/A2B/001.png"
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
