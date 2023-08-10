import * as style from './style';

interface OptionDescriptionProps {
  name: string;
  additionalPrice: number;
  tags: string[];
}

export function OptionDescription({ name, additionalPrice, tags }: OptionDescriptionProps) {
  return (
    <style.Container>
      <style.TitleWrapper>
        <style.TitleBox>{name}</style.TitleBox>
        <style.PriceBox>+{additionalPrice.toLocaleString()}원</style.PriceBox>
      </style.TitleWrapper>
      <style.Line />
      <style.DescriptionWrapper>
        <style.SubTitleBox>{name}</style.SubTitleBox>
        <style.MessageBox>에 대해 시승자들은 이런 후기를 남겼어요</style.MessageBox>
      </style.DescriptionWrapper>
      <style.TagWrapper>
        {tags.map(tag => (
          <style.TagBox key={tag}>{tag}</style.TagBox>
        ))}
      </style.TagWrapper>
    </style.Container>
  );
}
