import { OptionDetailsProps } from '@/types/option';

import * as style from './style';

interface SelectOptionDetailProps {
  index: number;
  length: number;
  optionDetails: OptionDetailsProps[];
}

export function SelectOptionDetail({ index, length, optionDetails }: SelectOptionDetailProps) {
  const { title, description } = optionDetails[index];

  function convertToTwoDigits(index: number) {
    return (index + 1).toString().padStart(2, '0');
  }

  return (
    <>
      <style.Container>
        <style.TitleWrapper>
          <style.TitleBox>
            <style.Ellipse>{convertToTwoDigits(index)}</style.Ellipse>
            <style.Title>{title}</style.Title>
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
    </>
  );
}
