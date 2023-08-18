import { MutableRefObject } from 'react';

import * as Styled from './style';

interface Props {
  subOptionNames: string[];
  childRef: MutableRefObject<HTMLUListElement | null>;
}

export function OptionCardHover({ subOptionNames, childRef }: Props) {
  return (
    <Styled.Container>
      <Styled.DescriptionWrapper ref={childRef}>
        {subOptionNames.map((name, index) => (
          <Styled.DescriptionHover key={index}>{name}</Styled.DescriptionHover>
        ))}
      </Styled.DescriptionWrapper>
    </Styled.Container>
  );
}
