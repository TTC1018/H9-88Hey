import { SelectOptionCardInfoProps } from '@/types/option';
import { DefaultOptionCardInfoProps } from '@/types/option';

import { OptionCategory } from './OptionCategory';
import { SelectOptionCard } from './SelectOptionCard';
import { DefaultOptionCard } from './DefaultOptionCard';

interface OptionCardContainerProps {
  selectedCategory: number;
  selectedOption: number;
  selectOptionCardInfo: SelectOptionCardInfoProps[];
  defaultOptionCardInfo: DefaultOptionCardInfoProps[];
  categories: string[];
}

export function OptionCardContainer({
  selectedCategory,
  selectedOption,
  selectOptionCardInfo,
  defaultOptionCardInfo,
  categories,
}: OptionCardContainerProps) {
  return (
    <>
      <OptionCategory selectedCategory={selectedCategory} />
      <SelectOptionCard selectedOption={selectedOption} cardInfo={selectOptionCardInfo} />
      <DefaultOptionCard
        selectedCategory={selectedCategory}
        categories={categories}
        defaultOptionCardInfo={defaultOptionCardInfo}
      />
    </>
  );
}
