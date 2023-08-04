import * as style from './style';

interface OptionCategoryProps {
  selectedCategory: number;
}

export function OptionCategory({ selectedCategory }: OptionCategoryProps) {
  return (
    <style.OptionWrapper>
      <style.Option option={1} selectedCategory={selectedCategory} onClick={() => {}}>
        선택 항목
      </style.Option>
      <style.Option option={2} selectedCategory={selectedCategory} onClick={() => {}}>
        기본 포함 품목
      </style.Option>
    </style.OptionWrapper>
  );
}
