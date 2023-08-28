import { useState, useEffect } from 'react';

import { useOutletContext } from 'react-router-dom';

import { DefaultOptionDataProps, SubOptionProps } from '@/types/option';
import { MyCarLayoutContextProps } from '@/types/trim';
import { isValidIndex, isIndexLargeThanZero, isIndexSmallThanMaxIndex } from '@/utils';
import { OPTION_CARD_LIST_LENGTH } from '@/constants';
import { useFetch } from '@/hooks/useFetch';

import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';
import { OptionModalProvider } from '@/components/Option/OptionModalProvider';
import { OptionModal } from '@/components/Option/OptionModal';

import * as Styled from './style';

const initialData = {
  defaultOptions: [
    {
      category: '',
      subOptions: [
        {
          name: '',
          imageUrl: '',
          description: '',
        },
      ],
    },
  ],
};

export function DefaultOptionCardList() {
  const { carCode } = useOutletContext<MyCarLayoutContextProps>();

  const { data } = useFetch<DefaultOptionDataProps>({
    defaultValue: initialData,
    url: `/car/default-option?car_code=${carCode.current}`,
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
    <Styled.Container>
      <Styled.CategoryWrapper>
        {categories.map((category, index) => (
          <Styled.Category
            isActive={index === categoryIndex}
            onClick={() => handleChangeCategoryIndex(index)}
            key={category}
          >
            {category}
          </Styled.Category>
        ))}
      </Styled.CategoryWrapper>
      <Styled.OptionCardWrapper>
        <PrevButton
          width="48"
          height="48"
          onClick={() => handleChangeCardListIndex(cardListIndex - 1, subOptions.length)}
          isShow={isIndexLargeThanZero(cardListIndex)}
        />
        {cardList.map(({ name, imageUrl, description }, index) => (
          <Styled.OptionCard key={index} onClick={() => handleOpenModal(name, imageUrl, description)}>
            <Styled.Image src={imageUrl} />
            <Styled.TextWrapper>
              <Styled.Text>{name}</Styled.Text>
              <Styled.SubText>기본 포함</Styled.SubText>
            </Styled.TextWrapper>
          </Styled.OptionCard>
        ))}
        <NextButton
          width="48"
          height="48"
          onClick={() => handleChangeCardListIndex(cardListIndex + 1, subOptions.length)}
          isShow={isIndexSmallThanMaxIndex(cardListIndex, subOptions.length)}
        />
      </Styled.OptionCardWrapper>
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
    </Styled.Container>
  );
}
