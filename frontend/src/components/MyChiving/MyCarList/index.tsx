import { MouseEvent } from 'react';

import { formatDate } from '@/utils';
import { MyChivingProps } from '@/types/myChiving';

import { XButton } from '@/components/MyChiving/XButton';
import { ColorCircle } from '@/components/common/ColorCircle';

import * as Styled from './style';

interface ClickEventDataProps {
  deleteText: string;
  moveText: string;
}

interface MyCarListProps {
  myChiving: MyChivingProps;
  onClick: (myChiving: MyChivingProps, data: ClickEventDataProps, event: MouseEvent<HTMLDivElement>) => void;
}

export function MyCarList({ myChiving, onClick }: MyCarListProps) {
  const {
    isSaved,
    model,
    trim,
    engine,
    bodyType,
    wheelDrive,
    lastModifiedDate,
    selectOptions,
    exteriorColor,
    interiorColor,
  } = myChiving;

  const date = formatDate(lastModifiedDate);
  const dateInfoText = isSaved ? `${date}에 만들었어요.` : `${date} 임시저장`;
  const trimOptions = `${engine?.name ? engine.name : ''}${bodyType?.name ? ` / ${bodyType.name}` : ''} ${
    wheelDrive?.name ? ` / ${wheelDrive.name}` : ''
  }`;

  return (
    <Styled.Container
      onClick={event => onClick(myChiving, { deleteText: `${model.name} ${trim.name}`, moveText: `${date}` }, event)}
    >
      <Styled.Wrapper>
        <Styled.InfoBox>
          {!isSaved && <Styled.InfoText>저장하지 않고 나간 차량이 있어요.</Styled.InfoText>}
        </Styled.InfoBox>
        <Styled.MainBox>
          <Styled.InfoClosure>
            <Styled.Title>
              <Styled.TitleText>
                {model.name} {trim.name}
              </Styled.TitleText>
              <Styled.TrimText>{trimOptions}</Styled.TrimText>
            </Styled.Title>
            {exteriorColor && interiorColor && (
              <Styled.ColorBox>
                <ColorCircle imageUrl={exteriorColor?.colorImageUrl} />
                <Styled.ColorName>{exteriorColor.name}</Styled.ColorName>
                <ColorCircle imageUrl={interiorColor?.colorImageUrl} />
                <Styled.ColorName>{interiorColor.name}</Styled.ColorName>
              </Styled.ColorBox>
            )}
          </Styled.InfoClosure>
          <Styled.SubTitle>
            <Styled.SubTitleText isSaved={isSaved}>{dateInfoText}</Styled.SubTitleText>
            <XButton onClick={() => {}} />
          </Styled.SubTitle>
        </Styled.MainBox>
        <Styled.OptionBox>
          {selectOptions && selectOptions.length > 0
            ? selectOptions.map((option, index) => (
                <Styled.OptionCard key={index} imageUrl={option.imageUrl}>
                  <Styled.OptionCardText>{option.name}</Styled.OptionCardText>
                </Styled.OptionCard>
              ))
            : [1, 2, 3, 4].map(item => <Styled.EmptyOptionCard key={item} />)}
        </Styled.OptionBox>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
