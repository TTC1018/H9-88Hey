import { useState, useEffect } from 'react';

import { DefaultOptionDataProps, SubOptionProps } from '@/types/option';
import { isValidIndex, isIndexLargeThanZero, isIndexSmallThanMaxIndex } from '@/utils';
import { OPTION_CARD_LIST_LENGTH } from '@/constants';
import { useFetch } from '@/hooks/useFetch';

import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';
import { OptionModalProvider } from '@/components/common/OptionModalProvider';
import { OptionModal } from '@/components/common/OptionModal';

import * as style from './style';

const initialData = {
  defaultOptions: [
    {
      category: '',
      subOptions: [
        {
          id: 1,
          name: '',
          imageUrl: '',
          description: '',
        },
      ],
    },
  ],
};

export function DefaultOptionCardList() {
  const { data } = useFetch<DefaultOptionDataProps>({
    defaultValue: initialData,
    url: '/model/1/trim/2/default_option',
  });

  const [categories, setCategories] = useState<string[]>([]);
  const [subOptions, setSubOptions] = useState<SubOptionProps[]>([]);
  const [cardList, setCardList] = useState<SubOptionProps[]>([]);

  const [categoryIndex, setCategoryIndex] = useState(0);
  const [cardListIndex, setCardListIndex] = useState(0);

  const [isShowModal, setIsShowModal] = useState(false);
  const [modal, setModal] = useState({
    name: '',
    imageUrl: '',
    description: '',
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

  function handleOpenModal(name: string, imageUrl: string, description: string) {
    setIsShowModal(true);
    setModal({ name, imageUrl, description });
  }

  function handleCloseModal() {
    setIsShowModal(false);
  }

  // TODO: 커스텀 훅으로 빼기
  useEffect(() => {
    const { defaultOptions } = data;

    const cardListData = defaultOptions[categoryIndex].subOptions.map(({ id, name, imageUrl, description }) => ({
      id,
      name,
      imageUrl,
      description,
    }));

    const startIndex = cardListIndex * OPTION_CARD_LIST_LENGTH;
    let endIndex = startIndex + OPTION_CARD_LIST_LENGTH;
    if (endIndex > cardListData.length) {
      endIndex = cardListData.length;
    }

    setCategories(defaultOptions.map(({ category }) => category));
    setSubOptions(defaultOptions[categoryIndex].subOptions);
    setCardList(cardListData.slice(startIndex, endIndex));
  }, [data, categoryIndex, cardListIndex]);

  return (
    <style.Container>
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
        {cardList.map(({ name, imageUrl, description }, index) => (
          <style.OptionCard key={index} onClick={() => handleOpenModal(name, imageUrl, description)}>
            <style.Image src={imageUrl} />
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
          <OptionModal
            name={modal.name}
            imageUrl={modal.imageUrl}
            description={modal.description}
            onClick={handleCloseModal}
          />
        </OptionModalProvider>
      )}
    </style.Container>
  );
}
