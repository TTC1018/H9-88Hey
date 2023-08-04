import { SelectOptionCardInfoProps } from '@/types/option';
import { DefaultOptionCardInfoProps } from '@/types/option';

import { OptionCategory } from './OptionCategory';
import { SelectOptionCard } from './SelectOptionCard';
import { DefaultOptionCard } from './DefaultOptionCard';

interface OptionCardContainerProps {
  isSelectOption: boolean;
  selectOption: number;
  defaultOption: number;
  selectOptionCardInfo: SelectOptionCardInfoProps[];
  defaultOptionCardInfo: DefaultOptionCardInfoProps[];
  categories: string[];
  activeButtons: Set<number>;
  onClickCategoryButton: (isSelectOption: boolean) => void;
}

export function OptionCardContainer({
  isSelectOption,
  selectOption,
  defaultOption,
  selectOptionCardInfo,
  defaultOptionCardInfo,
  categories,
  activeButtons,
  onClickCategoryButton,
}: OptionCardContainerProps) {
  return (
    <>
      <OptionCategory isSelectOption={isSelectOption} onClickCategoryButton={onClickCategoryButton} />
      {isSelectOption ? (
        <SelectOptionCard selectOption={selectOption} cardInfo={selectOptionCardInfo} activeButtons={activeButtons} />
      ) : (
        <DefaultOptionCard
          defaultOption={defaultOption}
          categories={categories}
          defaultOptionCardInfo={defaultOptionCardInfo}
        />
      )}
    </>
  );
}
