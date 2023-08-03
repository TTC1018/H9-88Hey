import * as style from './style';

interface OptionCategoryProps {
  selectedOption: number;
}

export function OptionCategory({ selectedOption }: OptionCategoryProps) {
  return (
    <>
      <style.OptionWrapper>
        <style.Option option={1} selectedOption={selectedOption} onClick={() => {}}>
          선택 항목
        </style.Option>
        <style.Option option={2} selectedOption={selectedOption} onClick={() => {}}>
          기본 포함 품목
        </style.Option>
      </style.OptionWrapper>
    </>
  );
}
