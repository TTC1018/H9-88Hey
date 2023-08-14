import { useLocation } from 'react-router-dom';

import * as Styled from './style';
import { DetailHeader } from '@/components/Archiving/DetailHeader';
import { ArchivingProps } from '@/types/archiving';
import { combineWithSlash, formatDate } from '@/utils';
import { DetailDescription } from '@/components/Archiving/DetailDescription';
import { OptionDescriptionCard } from '@/components/Archiving/OptionDescriptionCard';
import { OptionList } from '@/components/Archiving/OptionList';

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

  const itemsPerRow = 3; // 각 줄에 표시할 아이템 개수

  const rows = [];
  for (let i = 0; i < selectedOptions.length; i += itemsPerRow) {
    rows.push(selectedOptions.slice(i, i + itemsPerRow));
  }

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
        <OptionList options={selectedOptions} />
      </Styled.OptionWrapper>
    </Styled.Container>
  );
}
