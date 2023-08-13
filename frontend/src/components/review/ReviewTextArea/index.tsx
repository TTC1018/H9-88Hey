import { ChangeEvent, useState } from 'react';

import { MAX_TEXT_LENGTH } from '@/constants';

import * as Styled from './style';

export function ReviewTextArea() {
  const [text, setText] = useState('');

  const isActive = text.length >= MAX_TEXT_LENGTH;

  function handleText({ target }: ChangeEvent<HTMLTextAreaElement>) {
    const newText = target.value;

    if (newText.length <= MAX_TEXT_LENGTH) {
      setText(newText);
    }
  }

  return (
    <Styled.Container>
      <Styled.TextWrapper>
        <Styled.TextBox>
          <Styled.Title>리뷰를 남겨주세요</Styled.Title>
          <Styled.SubTitle isActive={false}>(선택사항)</Styled.SubTitle>
        </Styled.TextBox>
        <Styled.SubTitle isActive={isActive}>({text.length}/140)</Styled.SubTitle>
      </Styled.TextWrapper>
      <Styled.TextArea onChange={handleText} value={text} placeholder="직접 옵션에 대해 이야기해주세요!" />
    </Styled.Container>
  );
}
