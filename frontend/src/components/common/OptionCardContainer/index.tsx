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

interface SelectOptionCardInfoProps {
  image: string;
  title: string;
  price: number;
  descriptions: string[];
}

interface DefaultOptionCardInfoProps {
  image: string;
  text: string;
  subtext: string;
  description: string;
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
