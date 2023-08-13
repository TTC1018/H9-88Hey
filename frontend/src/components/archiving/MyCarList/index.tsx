import { XButton } from '@/components/archiving/XButton';

import * as Styled from './style';

interface selectedOptionProps {
  name: string;
  imageUrl: string;
}

interface MyCarListProps {
  isSaved: boolean;
  model: string;
  trim: string;
  trimOptions: string[];
  lastModifiedDate: string;
  selectedOptions: selectedOptionProps[];
}

export function MyCarList({ isSaved, model, trim, trimOptions, lastModifiedDate, selectedOptions }: MyCarListProps) {
  const modifiedDate = lastModifiedDate.split('-');
  const date = `${modifiedDate[0].slice(2)}년 ${modifiedDate[1]}월 ${modifiedDate[2]}일`;

  function handleClick() {
    // 모달창 출력
  }
  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.InfoBox>
          {!isSaved && <Styled.InfoText>저장하지 않고 나간 차량이 있어요.</Styled.InfoText>}
        </Styled.InfoBox>
        <Styled.MainBox>
          <Styled.Title>
            <Styled.TitleText>
              {model} {trim}
            </Styled.TitleText>
            <Styled.TrimText>{trimOptions.join(' / ')}</Styled.TrimText>
          </Styled.Title>
          <Styled.SubTitle>
            <Styled.SubTitleText isSaved={isSaved}>
              {isSaved ? `${date}에 만들었어요.` : `${date} 임시저장`}
            </Styled.SubTitleText>
            <XButton onClick={handleClick} />
          </Styled.SubTitle>
        </Styled.MainBox>
        <Styled.OptionBox>
          {selectedOptions.length !== 0 ? (
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
