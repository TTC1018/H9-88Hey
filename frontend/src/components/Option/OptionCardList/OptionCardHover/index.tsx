import { Ref, forwardRef, FC } from 'react';

import * as Styled from './style';

interface Props {
  subOptionNames: string[];
  ref: Ref<HTMLUListElement>;
}

export const OptionCardHover: FC<Props> = forwardRef((props, ref) => {
  const { subOptionNames } = props;

  return (
    <Styled.Container>
      <Styled.DescriptionWrapper ref={ref}>
        {subOptionNames.map((name, index) => (
          <Styled.DescriptionHover key={index}>{name}</Styled.DescriptionHover>
        ))}
      </Styled.DescriptionWrapper>
    </Styled.Container>
  );
});
