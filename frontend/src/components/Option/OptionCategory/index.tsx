import * as Styled from './style';

interface OptionCategoryProps {
  menu: number;
  onClick: (menu: number) => void;
  isShowDefaultOption: boolean;
}

export function OptionCategory({ menu, onClick, isShowDefaultOption }: OptionCategoryProps) {
  return (
    <Styled.OptionWrapper>
      <Styled.Option isActive={menu === 0} onClick={() => onClick(0)}>
        선택 항목
      </Styled.Option>
      {isShowDefaultOption && (
        <Styled.Option isActive={menu === 1} onClick={() => onClick(1)}>
          기본 포함 품목
        </Styled.Option>
      )}
    </Styled.OptionWrapper>
  );
}
