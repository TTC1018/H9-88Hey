import { OptionContextProps } from '@/types/option';

import { OptionCard } from '@/components/Result/OptionCard';

import * as Styled from './style';

interface Props {
  options: OptionContextProps[];
}

export function OptionCardList({ options }: Props) {
  return (
    <>
      <Styled.OptionTitleWrapper>
        <Styled.OptionTitleBox>
          <Styled.OptionTitle>선택 옵션</Styled.OptionTitle>
          <Styled.OptionCount>{options.length}</Styled.OptionCount>
          <Styled.OptionUnit>개</Styled.OptionUnit>
        </Styled.OptionTitleBox>
      </Styled.OptionTitleWrapper>
      <Styled.OptionCardWrapper>
        <Styled.OptionCardBox>
          {options.map(({ name, price, imageUrl, subOptions }, index) => (
            <OptionCard
              imageUrl={imageUrl}
              name={name}
              price={price}
              subOptions={subOptions}
              index={index}
              key={name}
            />
          ))}
        </Styled.OptionCardBox>
      </Styled.OptionCardWrapper>
    </>
  );
}
