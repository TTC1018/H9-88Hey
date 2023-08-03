import { OptionCategory } from './OptionCategory';
import { SelectOptionCard } from './SelectOptionCard';

interface OptionCardContainerProps {
  selectedCategory: number;
  selectedOption: number;
  images: string[];
}

export function OptionCardContainer({ selectedCategory, selectedOption, images }: OptionCardContainerProps) {
  return (
    <>
      <OptionCategory selectedCategory={selectedCategory} />
      <SelectOptionCard selectedOption={selectedOption} images={images} />
    </>
  );
}
