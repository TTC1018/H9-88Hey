import * as Styled from './style';

interface OptionDescriptionProps {
  name: string;
  additionalPrice: number;
  tags: string[];
}

export function OptionDescription({ name, additionalPrice, tags }: OptionDescriptionProps) {
  return (
    <Styled.Container>
      <Styled.TitleWrapper>
        <Styled.TitleBox>{name}</Styled.TitleBox>
        <Styled.PriceBox>+{additionalPrice.toLocaleString()}원</Styled.PriceBox>
      </Styled.TitleWrapper>
      <Styled.Line />
      <Styled.DescriptionWrapper>
        <Styled.SubTitleBox>{name}</Styled.SubTitleBox>
        <Styled.MessageBox>에 대해 시승자들은 이런 후기를 남겼어요</Styled.MessageBox>
      </Styled.DescriptionWrapper>
      <Styled.TagWrapper>
        {tags.map(tag => (
          <Styled.TagBox key={tag}>{tag}</Styled.TagBox>
        ))}
      </Styled.TagWrapper>
    </Styled.Container>
  );
}
