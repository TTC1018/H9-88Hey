import { DefaultOptionCardDataProps } from '@/types/option';

import * as style from './style';

interface DefaultOptionCardListProps {
  isShow: boolean;
  categoryIndex: number;
  categories: string[];
  data: DefaultOptionCardDataProps[];
  onClickCategory: (index: number) => void;
}

export function DefaultOptionCardList({
  isShow,
  categoryIndex,
  categories,
  data,
  onClickCategory,
}: DefaultOptionCardListProps) {
  return (
    <style.Container isShow={isShow}>
      <style.CategoryWrapper>
        {categories.map((category, index) => (
          <style.Category isActive={index === categoryIndex} onClick={() => onClickCategory(index)} key={category}>
            {category}
          </style.Category>
        ))}
      </style.CategoryWrapper>
      <style.OptionCardWrapper>
        {data.map(({ name, imageUrl }, index) => (
          <style.OptionCard key={index}>
            <style.Image src={imageUrl} />
            <style.TextWrapper>
              <style.Text>{name}</style.Text>
              <style.SubText>기본 포함</style.SubText>
            </style.TextWrapper>
          </style.OptionCard>
        ))}
      </style.OptionCardWrapper>
    </style.Container>
  );
}
