import { Suspense } from 'react';

import { TagBox } from '@/components/common/TagBox';
import { TagSkeleton } from '@/components/common/TagSkeleton';

import * as Styled from './style';

interface OptionDescriptionProps {
  name: string;
  additionalPrice: number;
  tagId: string;
}

export function OptionDescription({ name, additionalPrice, tagId }: OptionDescriptionProps) {
  return (
    <Styled.Container>
      <Styled.TitleWrapper>
        <Styled.TitleBox>{name}</Styled.TitleBox>
        <Styled.PriceBox>+{additionalPrice.toLocaleString()}원</Styled.PriceBox>
      </Styled.TitleWrapper>
      <Styled.Line />
      <Styled.DescriptionWrapper>
        <Styled.SubTitleBox>{name}</Styled.SubTitleBox>
        <Styled.MessageBox>에 대해 시승자들은 이런 후기를 남겼어요</Styled.MessageBox>
      </Styled.DescriptionWrapper>
      <Styled.TagWrapper>
        <Suspense fallback={<TagSkeleton count={3} />}>
          <TagBox tagId={tagId} type={'select-option'} limit={3} />
        </Suspense>
      </Styled.TagWrapper>
    </Styled.Container>
  );
}
