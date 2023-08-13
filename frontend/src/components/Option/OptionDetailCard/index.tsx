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
  return (
    <Styled.Container>
      <Styled.TitleWrapper>
        <Styled.TitleBox>
          <Styled.Ellipse>{convertToTwoDigits(index)}</Styled.Ellipse>
          <Styled.Title>{name}</Styled.Title>
        </Styled.TitleBox>
        <Styled.OrderBox>
          <Styled.Order>
            {index + 1}/{length}
          </Styled.Order>
        </Styled.OrderBox>
      </Styled.TitleWrapper>
      <Styled.Line />
      <Styled.DescriptionWrapper>
        <PrevButton width="48" height="48" onClick={() => onClick(index - 1)} />
        <Styled.Description>{description}</Styled.Description>
        <NextButton width="48" height="48" onClick={() => onClick(index + 1)} />
      </Styled.DescriptionWrapper>
    </Styled.Container>
  );
}
