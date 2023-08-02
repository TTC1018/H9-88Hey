import * as style from './style';

interface SelectOptionDescriptionProps {
  title: string;
  price: string;
  tags: string[];
}

export function SelectOptionDescription({ title, price, tags }: SelectOptionDescriptionProps) {
  return (
    <style.Container>
      <style.TitleWrapper>
        <style.TitleBox>{title}</style.TitleBox>
        <style.PriceBox>+{price}원</style.PriceBox>
      </style.TitleWrapper>
      <style.Line />
      <style.DescriptionWrapper>
        <style.SubTitleBox>{title}</style.SubTitleBox>
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
