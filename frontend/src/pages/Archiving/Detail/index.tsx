import { useLocation } from 'react-router-dom';

import { ArchivingProps } from '@/types/archiving';
import { combineWithSlash, formatDate } from '@/utils';

import { OptionList } from '@/components/Archiving/OptionList';
import { DetailHeader } from '@/components/Archiving/DetailHeader';
import { DetailDescription } from '@/components/Archiving/DetailDescription';

import * as Styled from './style';

export function Detail() {
  const location = useLocation();
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

  const dateText = isPurchase ? '에 구매했어요' : '에 시승했어요';
  return (
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
          onClickStartButton={() => {}}
        />
      </Styled.DescriptionWrapper>
      <Styled.Line />
      <Styled.OptionWrapper>
        <OptionList options={selectedOptions} />
      </Styled.OptionWrapper>
    </Styled.Container>
  );
}
