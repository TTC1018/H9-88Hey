import { SelectOptionsProps } from '@/types/archiving';

import { OptionDescriptionCard } from '@/components/Archiving/OptionDescriptionCard';

import * as Styled from './style';

interface Props {
  options: SelectOptionsProps[];
}
export function OptionList({ options }: Props) {
  const contents: SelectOptionsProps[][] = [[], [], []];

  const ITEM_ROW = 3;

  options.forEach((option, index) => {
    contents[index % ITEM_ROW].push(option);
  });

  return (
    <Styled.Container>
      {contents.map((content, index) => {
        return (
          <Styled.Wrapper key={index}>
            {content.map(props => {
              return <OptionDescriptionCard key={props.id} props={props} />;
            })}
          </Styled.Wrapper>
        );
      })}
    </Styled.Container>
  );
}
