import * as style from './style';

interface DefaultOptionCardProps {
  selectedCategory: number;
  categories: string[];
  defaultOptionCardInfo: DefaultOptionCardInfoProps[];
}

interface DefaultOptionCardInfoProps {
  image: string;
  text: string;
  subtext: string;
  description: string;
}

export function DefaultOptionCard({ selectedCategory, categories, defaultOptionCardInfo }: DefaultOptionCardProps) {
  return (
    <style.Container>
      <style.CategoryWrapper>
        {categories.map((category, index) => (
          <style.Category category={index + 1} selectedCategory={selectedCategory} key={category}>
            {category}
          </style.Category>
        ))}
      </style.CategoryWrapper>
      <style.OptionCardWrapper>
        {defaultOptionCardInfo.map((info, index) => (
          <style.OptionCard key={index}>
            <style.Image src={info.image} />
            <style.TextWrapper>
              <style.Text>{info.text}</style.Text>
              <style.SubText>{info.subtext}</style.SubText>
            </style.TextWrapper>
          </style.OptionCard>
        ))}
      </style.OptionCardWrapper>
    </style.Container>
  );
}
