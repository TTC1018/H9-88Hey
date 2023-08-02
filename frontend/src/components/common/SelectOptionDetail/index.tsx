import * as style from './style';

interface SelectOptionDetailProps {
  index: number;
  length: number;
  optionList: optionListProps[];
}

interface optionListProps {
  title: string;
  description: string;
}

export function SelectOptionDetail({ index, length, optionList }: SelectOptionDetailProps) {
  function convertToTwoDigits(index: number) {
    return (index + 1).toString().padStart(2, '0');
  }

  return (
    <>
      <style.Container>
        <style.TitleWrapper>
          <style.TitleBox>
            <style.Ellipse>{convertToTwoDigits(index)}</style.Ellipse>
            <style.Title>{optionList[0].title}</style.Title>
          </style.TitleBox>
          <style.OrderBox>
            <style.Order>
              {index + 1}/{length}
            </style.Order>
          </style.OrderBox>
        </style.TitleWrapper>
        <style.Line />
        <style.DescriptionWrapper>
          <style.Description>{optionList[0].description}</style.Description>
        </style.DescriptionWrapper>
      </style.Container>
    </>
  );
}
