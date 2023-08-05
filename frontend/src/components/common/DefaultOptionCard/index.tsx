import { DefaultOptionCardInfoProps } from '@/types/option';

import * as style from './style';

interface DefaultOptionCardProps {
  defaultOption: number;
  categories: string[];
  defaultOptionCardInfo: DefaultOptionCardInfoProps[];
}

export function DefaultOptionCard({ defaultOption, categories, defaultOptionCardInfo }: DefaultOptionCardProps) {
  return (
    <style.Container>
      <style.CategoryWrapper>
        {categories.map((category, index) => (
          <style.Category isActive={index + 1 === defaultOption} key={category}>
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
