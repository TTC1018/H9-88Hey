import { convertToTwoDigits } from '@/utils';

import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';

import * as style from './style';

interface OptionDetailCardProps {
  index: number;
  length: number;
  name: string;
  description: string;
  onClick: (step: number) => void;
}

export function OptionDetailCard({ index, length, name, description, onClick }: OptionDetailCardProps) {
  return (
    <style.Container>
      <style.TitleWrapper>
        <style.TitleBox>
          <style.Ellipse>{convertToTwoDigits(index)}</style.Ellipse>
          <style.Title>{name}</style.Title>
        </style.TitleBox>
        <style.OrderBox>
          <style.Order>
            {index + 1}/{length}
          </style.Order>
        </style.OrderBox>
      </style.TitleWrapper>
      <style.Line />
      <style.DescriptionWrapper>
        <PrevButton width="48" height="48" onClick={() => onClick(index - 1)} />
        <style.Description>{description}</style.Description>
        <NextButton width="48" height="48" onClick={() => onClick(index + 1)} />
      </style.DescriptionWrapper>
    </style.Container>
  );
}
