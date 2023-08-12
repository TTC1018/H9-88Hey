import { XButton } from '@/components/common/XButton';
import * as style from './style';

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
    <style.Container>
      <style.Wrapper>
        <style.InfoBox>{!isSaved && <style.InfoText>저장하지 않고 나간 차량이 있어요.</style.InfoText>}</style.InfoBox>
        <style.MainBox>
          <style.Title>
            <style.TitleText>
              {model} {trim}
            </style.TitleText>
            <style.TrimText>{trimOptions.join(' / ')}</style.TrimText>
          </style.Title>
          <style.SubTitle>
            <style.SubTitleText isSaved={isSaved}>
              {isSaved ? `${date}에 만들었어요.` : `${date} 임시저장`}
            </style.SubTitleText>
            <XButton onClick={handleClick} />
          </style.SubTitle>
        </style.MainBox>
        <style.OptionBox>
          {selectedOptions.length !== 0 ? (
            selectedOptions.map((option, index) => (
              <style.OptionCard key={index} imageUrl={option.imageUrl}>
                <style.OptionCardText>{option.name}</style.OptionCardText>
              </style.OptionCard>
            ))
          ) : (
            <style.OptionBoxText>선택한 옵션이 없습니다.</style.OptionBoxText>
          )}
        </style.OptionBox>
      </style.Wrapper>
    </style.Container>
  );
}
