import { useState } from 'react';

import * as style from './style';

interface OptionDetailCardProps {
  index: number;
  length: number;
  name: string;
  description: string;
}

// TODO: utils로 분리
function convertToTwoDigits(index: number) {
  return (index + 1).toString().padStart(2, '0');
}

export function OptionDetailCard({ index, length, name, description }: OptionDetailCardProps) {
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
        <style.Description>{description}</style.Description>
      </style.DescriptionWrapper>
    </style.Container>
  );
}
