import { OptionCategory } from './OptionCategory';
import { SelectOptionCard } from './SelectOptionCard';

interface OptionCardContainerProps {
  selectedOption: number;
  images: string[];
}

export function OptionCardContainer({ selectedOption, images }: OptionCardContainerProps) {
  return (
    <>
      <OptionCategory selectedOption={selectedOption} />
      <SelectOptionCard selectedOption={selectedOption} images={images} />
    </>
  );
}
