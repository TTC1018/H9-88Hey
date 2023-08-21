import * as Styled from './style';

interface Props {
  title: string;
  price: number;
}
export function MyCarDescription({ title, price }: Props) {
  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Title>{title}</Styled.Title>
        <Styled.Price>+{price.toLocaleString()}원</Styled.Price>
      </Styled.Wrapper>
      <Styled.Line />
    </Styled.Container>
  );
}
