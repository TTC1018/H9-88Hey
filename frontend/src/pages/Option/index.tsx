import { useState, useEffect, MouseEvent } from 'react';

import { useLocation } from 'react-router-dom';

import { OptionDataProps, OptionProps, SubOptionProps, OptionCardDataProps } from '@/types/option';
import { isValidIndex, checkIsSelectOptionPage } from '@/utils';
import { OPTION_CARD_LIST_LENGTH } from '@/constants';
import { useFetch } from '@/hooks/useFetch';

import { OptionImageBox } from '@/components/Option/OptionImageBox';
import { OptionDescription } from '@/components/Option/OptionDescription';
import { OptionDetailCard } from '@/components/Option/OptionDetailCard';
import { OptionCategory } from '@/components/Option/OptionCategory';
import { OptionCardList } from '@/components/Option/OptionCardList';
import { DefaultOptionCardList } from '@/components/Option/DefaultOptionCardList';

import * as Styled from './style';

const initialData = {
  selectOptions: [
    {
      isAvailable: true,
      id: '',
      name: '',
      imageUrl: '',
      additionalPrice: 0,
      tags: [],
      subOptions: [
        {
          id: '',
          name: '',
          imageUrl: '',
          description: '',
        },
      ],
    },
  ],
};

// 현재 API에서 tags를 null로 주고 있기 때문에 그동안 임시로 사용할 용도
const initialTags = ['여름에 쓰기 좋아요☀️', '옵션값 뽑았어요👍', '편리해요☺️'];

interface Props {
  apiType: string;
}

export function Option({ apiType }: Props) {
  const { pathname, search } = useLocation();

  const { data } = useFetch<OptionDataProps>({
    defaultValue: initialData,
    url: `/car/${apiType}${search}`,
  });

  const [option, setOption] = useState<OptionProps>({
    isAvailable: true,
    id: '',
    name: '',
    additionalPrice: 0,
    imageUrl: '',
    tags: [],
    subOptions: [],
  });
  const [subOption, setSubOption] = useState<SubOptionProps>({
    id: '',
    name: '',
    imageUrl: '',
    description: '',
  });
  const [cardListData, setCardListData] = useState<OptionCardDataProps[]>([]);

  const [menu, setMenu] = useState(0);
  const [cardListIndex, setCardListIndex] = useState(0);
  const [optionIndex, setOptionIndex] = useState(0);
  const [subOptionIndex, setSubOptionIndex] = useState(0);

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

  function handleChangeCardListIndex(index: number, length: number) {
    if (!isValidIndex(index, Math.ceil(length / OPTION_CARD_LIST_LENGTH) - 1)) {
      return;
    }
    setCardListIndex(index);
  }

  useEffect(() => {
    const { selectOptions } = data;

    const { isAvailable, id, name, additionalPrice, imageUrl, tags, subOptions } = selectOptions[optionIndex];

    const subOption = subOptions[subOptionIndex];
    const cardListData = selectOptions.map(
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

    setOption({ isAvailable, id, name, additionalPrice, imageUrl, tags: tags || initialTags, subOptions });
    setSubOption({
      id: subOption.id,
      name: subOption.name,
      imageUrl: subOption.imageUrl,
      description: subOption.description,
    });
    setCardListData(cardListData);
  }, [data, optionIndex, subOptionIndex]);

  return (
    <Styled.Container>
      <Styled.OptionWrapper>
        <Styled.ImageBox>
          <OptionImageBox imageUrl={subOption.imageUrl} />
        </Styled.ImageBox>
        <Styled.DescriptionBox>
          <OptionDescription name={option.name} additionalPrice={option.additionalPrice} tags={option.tags} />
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
          <OptionCardList
            selectedIndex={optionIndex}
            cardListIndex={cardListIndex}
            data={cardListData}
            onClickCard={handleChangeOptionIndex}
            onClickArrowButton={handleChangeCardListIndex}
          />
        )}
        {checkIsSelectOptionPage(pathname) && menu === 1 && <DefaultOptionCardList />}
      </Styled.CardWrapper>
    </Styled.Container>
  );
}
