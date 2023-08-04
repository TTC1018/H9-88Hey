import { OptionDetailsProps } from '@/types/option';
import { SelectOptionCardInfoProps } from '@/types/option';
import { DefaultOptionCardInfoProps } from '@/types/option';

import { SelectOptionImageBox } from '@/components/common/SelectOptionImageBox';
import { SelectOptionDescription } from '@/components/common/SelectOptionDescription';
import { SelectOptionDetail } from '@/components/common/SelectOptionDetail';
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
      <SelectOptionImageBox image={selectOptionCardInfo[index].image} />
      <SelectOptionDescription title={title} price={price} tags={tags} />
      <SelectOptionDetail index={index} length={selectOptionCardInfo.length} optionDetails={optionDetails} />
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
