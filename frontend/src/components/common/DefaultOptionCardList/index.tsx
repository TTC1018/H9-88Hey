import { useState, useEffect } from 'react';

import {
  DefaultOptionDataProps,
  DefaultOptionProps,
  DefaultSubOptionProps,
  DefaultOptionCardDataProps,
} from '@/types/option';
import { isValidIndex, isIndexLargeThanZero, isIndexSmallThanMaxIndex } from '@/utils';
import { OPTION_CARD_LIST_LENGTH } from '@/constants';
import { useFetch } from '@/hooks/useFetch';

import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';
import { OptionModalProvider } from '@/components/common/OptionModalProvider';
import { OptionModal } from '@/components/common/OptionModal';

import * as style from './style';

interface Props {
  isShow: boolean;
}

const initialData = {
  defaultOptions: [
    {
      category: '',
      subOptions: [
        {
          id: 1,
          name: '',
          imageURL: '',
          description: '',
        },
      ],
    },
  ],
};

export function DefaultOptionCardList({ isShow }: Props) {
  const { data } = useFetch<DefaultOptionDataProps>({
    defaultValue: initialData,
    url: '/model/1/trim/2/default_option',
  });

  const [defaultOptions, setDafaultOptions] = useState<DefaultOptionProps[]>([]);
  const [subOptions, setSubOptions] = useState<DefaultSubOptionProps[]>([]);
  const [cardList, setCardList] = useState<DefaultOptionCardDataProps[]>([]);
  const [categories, setCategories] = useState<string[]>([]);

  const [categoryIndex, setCategoryIndex] = useState(0);
  const [cardListIndex, setCardListIndex] = useState(0);

  const [isShowModal, setIsShowModal] = useState(false);
  const [modal, setModal] = useState({
    name: '',
    imageURL: '',
  });

  function handleChangeCategoryIndex(index: number) {
    if (index === categoryIndex) {
      return;
    }
    setCategoryIndex(index);
    setCardListIndex(0);
  }

  // TODO: 커스텀 훅으로 빼기
  function handleChangeCardListIndex(index: number, length: number) {
    if (!isValidIndex(index, Math.ceil(length / OPTION_CARD_LIST_LENGTH) - 1)) {
      return;
    }
    setCardListIndex(index);
  }

  function handleOpenModal(name: string, imageURL: string) {
    setIsShowModal(true);
    setModal({ name, imageURL });
  }

  function handleCloseModal() {
    setIsShowModal(false);
  }

  // TODO: 커스텀 훅으로 빼기
  useEffect(() => {
    const { defaultOptions } = data;

    const cardListData = defaultOptions[categoryIndex].subOptions.map(({ name, imageURL }) => ({
      name,
      imageURL,
    }));

    const startIndex = cardListIndex * OPTION_CARD_LIST_LENGTH;
    let endIndex = startIndex + OPTION_CARD_LIST_LENGTH;
    if (endIndex > cardListData.length) {
      endIndex = cardListData.length;
    }

    setDafaultOptions(defaultOptions);
    setSubOptions(defaultOptions[categoryIndex].subOptions);
    setCardList(cardListData.slice(startIndex, endIndex));
    setCategories(defaultOptions.map(({ category }) => category));
  }, [data, categoryIndex, cardListIndex]);

  return (
    <style.Container isShow={isShow}>
      <style.CategoryWrapper>
        {categories.map((category, index) => (
          <style.Category
            isActive={index === categoryIndex}
            onClick={() => handleChangeCategoryIndex(index)}
            key={category}
          >
            {category}
          </style.Category>
        ))}
      </style.CategoryWrapper>
      <style.OptionCardWrapper>
        <PrevButton
          width="48"
          height="48"
          onClick={() => handleChangeCardListIndex(cardListIndex - 1, subOptions.length)}
          isShow={isIndexLargeThanZero(cardListIndex)}
        />
        {cardList.map(({ name, imageURL }, index) => (
          <style.OptionCard key={index} onClick={() => handleOpenModal(name, imageURL)}>
            <style.Image src={imageURL} />
            <style.TextWrapper>
              <style.Text>{name}</style.Text>
              <style.SubText>기본 포함</style.SubText>
            </style.TextWrapper>
          </style.OptionCard>
        ))}
        <NextButton
          width="48"
          height="48"
          onClick={() => handleChangeCardListIndex(cardListIndex + 1, subOptions.length)}
          isShow={isIndexSmallThanMaxIndex(cardListIndex, subOptions.length)}
        />
      </style.OptionCardWrapper>
      {isShowModal && (
        <OptionModalProvider>
          <OptionModal name={modal.name} imageURL={modal.imageURL} onClick={handleCloseModal} />
        </OptionModalProvider>
      )}
    </style.Container>
  );
}
