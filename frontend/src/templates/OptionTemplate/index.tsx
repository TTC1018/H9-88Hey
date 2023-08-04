import { OptionDetailsProps } from '@/types/option';
import { SelectOptionCardInfoProps } from '@/types/option';
import { DefaultOptionCardInfoProps } from '@/types/option';

import { OptionImageBox } from '@/components/common/OptionImageBox';
import { OptionDescription } from '@/components/common/OptionDescription';
import { OptionDetailCard } from '@/components/common/OptionDetailCard';
import { OptionCardContainer } from '@/components/common/OptionCardContainer';

import * as style from './style';

interface OptionTemplateProps {
  title: string;
  price: number;
  tags: string[];
  index: number;
  isSelectOption: boolean;
  selectOption: number;
  defaultOption: number;
  optionDetails: OptionDetailsProps[];
  selectOptionCardInfo: SelectOptionCardInfoProps[];
  defaultOptionCardInfo: DefaultOptionCardInfoProps[];
  categories: string[];
  activeButtons: Set<number>;
  onClickCategoryButton: (isSelectOption: boolean) => void;
}

export function OptionTemplate({
  title,
  price,
  tags,
  index,
  isSelectOption,
  selectOption,
  defaultOption,
  optionDetails,
  selectOptionCardInfo,
  defaultOptionCardInfo,
  categories,
  activeButtons,
  onClickCategoryButton,
}: OptionTemplateProps) {
  return (
    <style.Container>
      <style.OptionWrapper>
        <style.ImageBox>
          <OptionImageBox image={selectOptionCardInfo[index].image} />
        </style.ImageBox>
        <style.DescriptionBox>
          <OptionDescription title={title} price={price} tags={tags} />
          <OptionDetailCard index={index} length={selectOptionCardInfo.length} optionDetails={optionDetails} />
        </style.DescriptionBox>
      </style.OptionWrapper>
      <style.CardWrapper>
        <OptionCardContainer
          isSelectOption={isSelectOption}
          selectOption={selectOption}
          defaultOption={defaultOption}
          selectOptionCardInfo={selectOptionCardInfo}
          defaultOptionCardInfo={defaultOptionCardInfo}
          categories={categories}
          activeButtons={activeButtons}
          onClickCategoryButton={onClickCategoryButton}
        />
      </style.CardWrapper>
    </style.Container>
  );
}
