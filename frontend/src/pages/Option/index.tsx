import { useState, MouseEvent } from 'react';

import { useLocation } from 'react-router-dom';

import { fetcher } from '@/utils/fetcher';
import { apiPath, cacheKey } from '@/constants';
import { useFetchSuspense } from '@/hooks/useFetchSuspense';
import { useDidMountEffect } from '@/hooks/useDidMountEffect';
import { isValidIndex, checkIsSelectOptionPage } from '@/utils';
import { OptionDataProps, OptionProps, SubOptionProps, OptionCardDataProps } from '@/types/option';

import { OptionImageBox } from '@/components/Option/OptionImageBox';
import { OptionDescription } from '@/components/Option/OptionDescription';
import { OptionDetailCard } from '@/components/Option/OptionDetailCard';
import { OptionCategory } from '@/components/Option/OptionCategory';
import { OptionCardList } from '@/components/Option/OptionCardList';
import { DefaultOptionCardList } from '@/components/Option/DefaultOptionCardList';

import * as Styled from './style';

interface Props {
  apiType: string;
}

export function Option({ apiType }: Props) {
  const { pathname, search } = useLocation();

  const { selectOptions } = useFetchSuspense<OptionDataProps>({
    fetcher: () => fetcher<OptionDataProps>({ url: apiPath.option(apiType, search) }),
    key: cacheKey.option(apiType, search),
  });

  if (selectOptions.length === 0) {
    return <div>dd</div>;
  }

  const [menu, setMenu] = useState(0);
  const [optionIndex, setOptionIndex] = useState(0);
  const [subOptionIndex, setSubOptionIndex] = useState(0);

  const { currentSelectOptions, currentSubOption, currentCardListData } = handleOptions();
  const [option, setOption] = useState<OptionProps>(currentSelectOptions);
  const [subOption, setSubOption] = useState<SubOptionProps>(currentSubOption);
  const [cardListData, setCardListData] = useState<OptionCardDataProps[]>(currentCardListData);

  function handleChangeMenu(menu: number) {
    setMenu(menu);
  }

  function handleChangeOptionIndex(index: number, event: MouseEvent<HTMLDivElement>) {
    const target = event.target as HTMLDivElement;
    if (index === optionIndex || target.tagName === 'BUTTON') {
      return;
    }
    setOptionIndex(index);
    setSubOptionIndex(0);
  }

  function handleChangeSubOptionIndex(index: number) {
    const { length } = option.subOptions;
    const newIndex = isValidIndex(index, length - 1) ? index : (index + length) % length;
    setSubOptionIndex(newIndex);
  }

  function handleOptions() {
    const currentSelectOptions = selectOptions[optionIndex];

    const currentSubOption = currentSelectOptions.subOptions[subOptionIndex];
    const currentCardListData = selectOptions.map(
      ({ isAvailable, id, name, additionalPrice, imageUrl, subOptions }, index) => ({
        isAvailable,
        id,
        index,
        name,
        additionalPrice,
        imageUrl,
        subOptionNames: subOptions.map(({ name }) => name),
      })
    );

    return { currentSelectOptions, currentSubOption, currentCardListData };
  }

  useDidMountEffect(() => {
    const {
      currentSelectOptions: { isAvailable, id, name, additionalPrice, imageUrl, subOptions },
      currentSubOption,
      currentCardListData,
    } = handleOptions();

    setOption({ isAvailable, id, name, additionalPrice, imageUrl, subOptions });
    setSubOption({
      ...currentSubOption,
    });
    setCardListData(currentCardListData);
  }, [selectOptions, optionIndex, subOptionIndex]);

  return (
    <Styled.Container>
      <Styled.OptionWrapper>
        <Styled.ImageBox>
          <OptionImageBox imageUrl={subOption.imageUrl} />
        </Styled.ImageBox>
        <Styled.DescriptionBox>
          <OptionDescription name={option.name} additionalPrice={option.additionalPrice} tagId={option.id} />
          <OptionDetailCard
            index={subOptionIndex}
            length={option.subOptions.length}
            name={subOption.name}
            description={subOption.description}
            onClick={handleChangeSubOptionIndex}
          />
        </Styled.DescriptionBox>
      </Styled.OptionWrapper>
      <Styled.CardWrapper>
        <OptionCategory
          menu={menu}
          onClick={handleChangeMenu}
          isShowDefaultOption={checkIsSelectOptionPage(pathname)}
        />
        {menu === 0 && (
          <OptionCardList selectedIndex={optionIndex} data={cardListData} onClickCard={handleChangeOptionIndex} />
        )}
        {checkIsSelectOptionPage(pathname) && menu === 1 && <DefaultOptionCardList />}
      </Styled.CardWrapper>
    </Styled.Container>
  );
}
