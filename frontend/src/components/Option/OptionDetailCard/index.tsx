import { convertToTwoDigits } from '@/utils';

import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';

import * as Styled from './style';

interface OptionDetailCardProps {
  index: number;
  length: number;
  name: string;
  description: string;
  onClick: (step: number) => void;
}

export function OptionDetailCard({ index, length, name, description, onClick }: OptionDetailCardProps) {
  const isEmpty = description === '-';
  const processedDescription = isEmpty ? '패키지 하위 옵션에 대한 설명이 없어요' : description;

  return (
    <Styled.Container>
      <Styled.TitleWrapper>
        <Styled.TitleBox>
          <Styled.Ellipse>{convertToTwoDigits(index)}</Styled.Ellipse>
          <Styled.Title>{name}</Styled.Title>
        </Styled.TitleBox>
        <Styled.Flex>
          <Styled.OrderBox>
            <Styled.Order>
              {index + 1}/{length}
            </Styled.Order>
          </Styled.OrderBox>
        </Styled.Flex>
      </Styled.TitleWrapper>
      <Styled.Line />
      <Styled.DescriptionWrapper>
        <PrevButton width="48" height="48" onClick={() => onClick(index - 1)} />
        <Styled.Description
          isEmpty={isEmpty}
          dangerouslySetInnerHTML={{ __html: processedDescription }}
        ></Styled.Description>
        <NextButton width="48" height="48" onClick={() => onClick(index + 1)} />
      </Styled.DescriptionWrapper>
    </Styled.Container>
  );
}
