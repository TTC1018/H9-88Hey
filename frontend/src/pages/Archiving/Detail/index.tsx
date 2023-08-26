import { Fragment } from 'react';

import { useLocation, useNavigate } from 'react-router-dom';

import { fetcher } from '@/utils/fetcher';
import { combineWithSlash, formatDate } from '@/utils';
import { ArchivingProps } from '@/types/archiving';
import { useModalContext } from '@/hooks/useModalContext';
import { useFetchSuspense } from '@/hooks/useFetchSuspense';
import { ModalType, OPTION_CATEGORY, apiPath, cacheKey } from '@/constants';

import { PopupModal } from '@/components/common/PopupModal';
import { ModalPortal } from '@/components/common/ModalPortal';
import { OptionList } from '@/components/Archiving/OptionList';
import { DetailHeader } from '@/components/Archiving/DetailHeader';
import { DetailDescription } from '@/components/Archiving/DetailDescription';

import * as Styled from './style';
import { OptionContextProps } from '@/types/option';

export function Detail() {
  const { search } = useLocation();
  const id = search.split('=')[1];

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
  } = useFetchSuspense<ArchivingProps>({
    fetcher: () => fetcher<ArchivingProps>({ url: apiPath.archivingDetail(id) }),
    key: cacheKey.archivingDetail(id),
  });

  const navigate = useNavigate();

  const options = selectedOptions.map(option => option.name);
  const dateText = `에 ${isPurchase ? '구매' : '시승'}했어요`;

  const myCar = {
    carType: { krName: '팰리세이드', enName: 'Palisade' },
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
        path: OPTION_CATEGORY[props.category],
      };
    }) as OptionContextProps[],
    carImageUrl: exteriorColor.carImageUrl,
  };

  const trimOptions = combineWithSlash([engine.name, bodyType.name, wheelDrive.name]);
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
            trimOptions={trimOptions}
            exteriorColor={exteriorColor.name}
            interiorColor={interiorColor.name}
            review={review}
            imageUrl={`${exteriorColor.carImageUrl}001.png`}
          />
        </Styled.HeaderWrapper>
        <Styled.DescriptionWrapper>
          <DetailDescription totalPrice={totalPrice} options={options} onClickStartButton={handleOpen} />
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
