import * as style from './style';

interface OptionCategoryProps {
  menu: number;
  onClick: (menu: number) => void;
}

export function OptionCategory({ menu, onClick }: OptionCategoryProps) {
  return (
    <style.OptionWrapper>
      <style.Option isActive={menu === 0} onClick={() => onClick(0)}>
        선택 항목
      </style.Option>
      <style.Option isActive={menu === 1} onClick={() => onClick(1)}>
        기본 포함 품목
      </style.Option>
    </style.OptionWrapper>
  );
}
