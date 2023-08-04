import * as style from './style';

interface OptionCategoryProps {
  isSelectOption: boolean;
  onClickCategoryButton: (isSelectOption: boolean) => void;
}

export function OptionCategory({ isSelectOption, onClickCategoryButton }: OptionCategoryProps) {
  return (
    <style.OptionWrapper>
      <style.Option
        isActive={isSelectOption}
        onClick={() => {
          onClickCategoryButton(true);
          console.log(isSelectOption);
        }}
      >
        선택 항목
      </style.Option>
      <style.Option
        isActive={!isSelectOption}
        onClick={() => {
          onClickCategoryButton(false);
          console.log(isSelectOption);
        }}
      >
        기본 포함 품목
      </style.Option>
    </style.OptionWrapper>
  );
}
