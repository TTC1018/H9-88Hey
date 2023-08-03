import * as style from './style';

interface MyCarDescriptionProps {
  title: string;
  price: string;
  hasTag: boolean;
  tags: string[];
}
export function MyCarDescription({ title, price, hasTag, tags }: MyCarDescriptionProps) {
  return (
    <style.Container>
      <style.Wrapper>
        <style.Title>
          {title}
          {hasTag && <style.SubTitle>에 대해 시승자들은 이런 후기를 남겼어요</style.SubTitle>}
        </style.Title>
        <style.Price>+{price}원</style.Price>
      </style.Wrapper>
      <style.Line />
      {hasTag && (
        <style.TagWrapper>
          {tags.map(tag => (
            <style.Tag key={tag}>{tag}</style.Tag>
          ))}
        </style.TagWrapper>
      )}
    </style.Container>
  );
}
