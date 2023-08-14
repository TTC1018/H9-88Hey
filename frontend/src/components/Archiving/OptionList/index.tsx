import { SelectOptionsProps } from '@/types/archiving';

import { OptionDescriptionCard } from '@/components/Archiving/OptionDescriptionCard';

import * as Styled from './style';

interface Props {
  options: SelectOptionsProps[];
}
export function OptionList({ options }: Props) {
  const itemsPerRow = 3;

  const contents: [SelectOptionsProps[], SelectOptionsProps[], SelectOptionsProps[]] = [[], [], []];
  options.forEach((option, index) => {
    contents[index % itemsPerRow].push(option);
  });

  return (
    <Styled.Container>
      {contents.map(content => {
        return (
          <Styled.Wrapper>
            {content.map(item => {
              return <OptionDescriptionCard props={item} />;
            })}
          </Styled.Wrapper>
        );
      })}
    </Styled.Container>
  );
}
