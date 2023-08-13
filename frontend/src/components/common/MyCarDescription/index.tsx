import * as Styled from './style';

interface MyCarDescriptionPropsWithTag {
  title: string;
  price: number;
  hasTag: true;
  tags: string[];
}
interface MyCarDescriptionPropsWithoutTags {
  title: string;
  price: number;
  hasTag: false;
  tags?: never;
}
type MyCarDescriptionProps = MyCarDescriptionPropsWithTag | MyCarDescriptionPropsWithoutTags;
export function MyCarDescription({ title, price, hasTag, tags }: MyCarDescriptionProps) {
  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Title>
          {title}
          {hasTag && <Styled.SubTitle>에 대해 시승자들은 이런 후기를 남겼어요</Styled.SubTitle>}
        </Styled.Title>
        <Styled.Price>+{price.toLocaleString()}원</Styled.Price>
      </Styled.Wrapper>
      <Styled.Line />
      {hasTag && (
        <Styled.TagWrapper>
          {tags.map(tag => (
            <Styled.Tag key={tag}>{tag}</Styled.Tag>
          ))}
        </Styled.TagWrapper>
      )}
    </Styled.Container>
  );
}
