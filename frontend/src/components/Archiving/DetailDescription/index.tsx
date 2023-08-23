import { useState } from 'react';

import { fetcher } from '@/utils/fetcher';
import { apiPath } from '@/constants';
import { usePostRequest } from '@/hooks/usePostRequest';
import { useFetchSuspense } from '@/hooks/useFetchSuspense';

import { SaveButton } from '@/components/common/SaveButton';

import * as Styled from './style';

interface Props {
  totalPrice: number;
  options: string[];
  onClickStartButton: () => void;
}
export function DetailDescription({ totalPrice, options, onClickStartButton }: Props) {
  const { bookmark } = useFetchSuspense<any>({
    fetcher: () =>
      fetcher<any>({
        url: apiPath.bookMark('479893076446129702'),
        fetchOptions: {
          headers: {
            Authorization: `Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiIxIiwidXNlck5hbWUiOiJ0ZXN0IiwiaWF0IjoxNjkyNTYwMzM5LCJleHAiOjQ4MTQ2MjQzMzl9.gcSE7kPaRVxo2iT9DRcN1Bn5ZNAAsHG8Z3dvTopH-IWblMf_LJ2lhsYqOvrrLcZJ`,
          },
        },
      }),
    key: ['bookMark', '479893076446129702'],
  });

  const [isActive, setIsActive] = useState(bookmark);

  const { postData } = usePostRequest(apiPath.bookMark('479893076446129702'));

  function handleSave() {
    const method = isActive ? 'DELETE' : 'POST';
    postData(method);
    setIsActive(!isActive);
  }

  return (
    <Styled.Container>
      <Styled.RegularText>총 가격</Styled.RegularText>
      <Styled.Price>{totalPrice.toLocaleString()}원</Styled.Price>
      <Styled.Wrapper>
        <Styled.Encloser>
          <Styled.MediumText>선택옵션</Styled.MediumText>
          <Styled.OptionBox>
            {options.map(option => (
              <Styled.Option key={option}>{option}</Styled.Option>
            ))}
          </Styled.OptionBox>
        </Styled.Encloser>
        <Styled.ButtonBox>
          <SaveButton onClick={handleSave} isActive={isActive} />
          <Styled.Button onClick={onClickStartButton}>이 차량으로 내 차 만들기 시작</Styled.Button>
        </Styled.ButtonBox>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
