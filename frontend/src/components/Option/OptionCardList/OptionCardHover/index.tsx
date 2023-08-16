import * as Styled from './style';

interface Props {
  subOptionNames: string[];
}

export function OptionCardHover({ subOptionNames }: Props) {
  return (
    <Styled.Container>
      <Styled.Wrapper>
        {subOptionNames.map((name, index) => (
          <Styled.DescriptionHover key={index}>·{name}</Styled.DescriptionHover>
        ))}
      </Styled.Wrapper>
    </Styled.Container>
  );
}
