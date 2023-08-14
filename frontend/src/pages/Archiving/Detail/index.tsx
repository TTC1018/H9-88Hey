import { useLocation } from 'react-router-dom';

import * as Styled from './style';
import { DetailHeader } from '@/components/archiving/DetailHeader';
import { ArchivingProps } from '@/types/archiving';
import { combineWithSlash, formatDate } from '@/utils';
import { DetailDescription } from '@/components/archiving/DetailDescription';
import { OptionDescriptionCard } from '@/components/archiving/OptionDescriptionCard';

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

  const dateText = isPurchase ? '구매했어요' : '시승했어요';
  return (
    <Styled.Container>
      <Styled.HeaderWrapper>
        <DetailHeader
          title={`${model} ${trim}`}
          date={formatDate(creationDate) + `에 ${dateText}`}
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
        {selectedOptions.map(option => (
          <OptionDescriptionCard props={option} />
        ))}
      </Styled.OptionWrapper>
    </Styled.Container>
  );
}
