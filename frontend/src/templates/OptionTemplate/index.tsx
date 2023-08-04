import { OptionDetailsProps } from '@/types/option';
import { SelectOptionCardInfoProps } from '@/types/option';
import { DefaultOptionCardInfoProps } from '@/types/option';

import { OptionImageBox } from '@/components/common/OptionImageBox';
import { OptionDescription } from '@/components/common/OptionDescription';
import { OptionDetailCard } from '@/components/common/OptionDetailCard';
import { OptionCardContainer } from '@/components/common/OptionCardContainer';

interface OptionTemplateProps {
  title: string;
  price: number;
  tags: string[];
  index: number;
  selectedCategory: number;
  selectedOption: number;
  optionDetails: OptionDetailsProps[];
  selectOptionCardInfo: SelectOptionCardInfoProps[];
  defaultOptionCardInfo: DefaultOptionCardInfoProps[];
  categories: string[];
}

export function OptionTemplate({
  title,
  price,
  tags,
  index,
  selectedCategory,
  selectedOption,
  optionDetails,
  selectOptionCardInfo,
  defaultOptionCardInfo,
  categories,
}: OptionTemplateProps) {
  return (
    <>
      <OptionImageBox image={selectOptionCardInfo[index].image} />
      <OptionDescription title={title} price={price} tags={tags} />
      <OptionDetailCard index={index} length={selectOptionCardInfo.length} optionDetails={optionDetails} />
      <OptionCardContainer
        selectedCategory={selectedCategory}
        selectedOption={selectedOption}
        selectOptionCardInfo={selectOptionCardInfo}
        defaultOptionCardInfo={defaultOptionCardInfo}
        categories={categories}
      />
    </>
  );
}
