import { OptionCategory } from './OptionCategory';

interface OptionCardContainerProps {
  selectedOption: number;
}

export function OptionCardContainer({ selectedOption }: OptionCardContainerProps) {
  return (
    <>
      <OptionCategory selectedOption={selectedOption} />
    </>
  );
}
