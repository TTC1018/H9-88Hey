import { SaveButton } from '@/components/common/SaveButton.tsx';

import * as Styled from './style';

interface Props {
  totalPrice: number;
  options: string[];
  onClickSaveButton: () => void;
  onClickStartButton: () => void;
}
export function DetailDescription({ totalPrice, options, onClickSaveButton, onClickStartButton }: Props) {
  return (
    <Styled.Container>
      <Styled.RegularText>총 가격</Styled.RegularText>
      <Styled.Price>{totalPrice.toLocaleString()}원</Styled.Price>
      <Styled.Wrapper>
        <Styled.Encloser>
          <Styled.MediumText>선택옵션</Styled.MediumText>
          <Styled.OptionBox>
            {options.map(option => (
              <Styled.Option key={option}>{option}</Styled.Option>
            ))}
          </Styled.OptionBox>
        </Styled.Encloser>
        <Styled.ButtonBox>
          <SaveButton onClick={onClickSaveButton} />
          <Styled.Button onClick={onClickStartButton}>이 차량으로 내 차 만들기 시작</Styled.Button>
        </Styled.ButtonBox>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
