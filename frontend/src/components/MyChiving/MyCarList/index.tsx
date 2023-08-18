import { MouseEvent } from 'react';

import { formatDate } from '@/utils';
import { MyChivingProps } from '@/types/myChiving';

import { XButton } from '@/components/MyChiving/XButton';

import * as Styled from './style';

interface OptionProps {
  name: string;
  imageUrl: string;
}

interface ClickEventDataProps {
  deleteText: string;
  moveText: string;
}

interface MyCarListProps {
  isSaved: boolean;
  lastModifiedDate: string;
  model?: string;
  trim?: string;
  engine?: string;
  bodyType?: string;
  wheelDrive?: string;
  selectedOptions?: OptionProps[];
  myChiving: MyChivingProps;
  onClick: (myChiving: MyChivingProps, data: ClickEventDataProps, event: MouseEvent<HTMLDivElement>) => void;
}

export function MyCarList({
  isSaved,
  model,
  trim,
  engine,
  bodyType,
  wheelDrive,
  lastModifiedDate,
  selectedOptions,
  myChiving,
  onClick,
}: MyCarListProps) {
  const date = formatDate(lastModifiedDate);
  const dateInfoText = isSaved ? `${date}에 만들었어요.` : `${date} 임시저장`;
  const trimOptions = `${engine}${bodyType !== '' ? ' / ' : ''}${bodyType}${
    wheelDrive !== '' ? ' / ' : ''
  }${wheelDrive}`;

  return (
    <Styled.Container
      onClick={event => onClick(myChiving, { deleteText: `${model} ${trim}`, moveText: `${date}` }, event)}
    >
      <Styled.Wrapper>
        <Styled.InfoBox>
          {!isSaved && <Styled.InfoText>저장하지 않고 나간 차량이 있어요.</Styled.InfoText>}
        </Styled.InfoBox>
        <Styled.MainBox>
          <Styled.Title>
            <Styled.TitleText>
              {model} {trim}
            </Styled.TitleText>
            <Styled.TrimText>{trimOptions}</Styled.TrimText>
          </Styled.Title>
          <Styled.SubTitle>
            <Styled.SubTitleText isSaved={isSaved}>{dateInfoText}</Styled.SubTitleText>
            <XButton />
          </Styled.SubTitle>
        </Styled.MainBox>
        <Styled.OptionBox>
          {selectedOptions && selectedOptions.length > 0 ? (
            selectedOptions.map((option, index) => (
              <Styled.OptionCard key={index} imageUrl={option.imageUrl}>
                <Styled.OptionCardText>{option.name}</Styled.OptionCardText>
              </Styled.OptionCard>
            ))
          ) : (
            <Styled.OptionBoxText>선택한 옵션이 없습니다.</Styled.OptionBoxText>
          )}
        </Styled.OptionBox>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
