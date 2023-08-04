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
}

export function OptionCardContainer({
  isSelectOption,
  selectOption,
  defaultOption,
  selectOptionCardInfo,
  defaultOptionCardInfo,
  categories,
  activeButtons,
}: OptionCardContainerProps) {
  return (
    <>
      <OptionCategory isSelectOption={isSelectOption} />
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
