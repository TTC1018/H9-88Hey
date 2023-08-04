import * as style from './style';

interface OptionCategoryProps {
  isSelectOption: boolean;
}

export function OptionCategory({ isSelectOption }: OptionCategoryProps) {
  return (
    <style.OptionWrapper>
      <style.Option isActive={isSelectOption} onClick={() => {}}>
        선택 항목
      </style.Option>
      <style.Option isActive={!isSelectOption} onClick={() => {}}>
        기본 포함 품목
      </style.Option>
    </style.OptionWrapper>
  );
}
