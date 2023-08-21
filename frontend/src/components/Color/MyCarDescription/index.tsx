import { Suspense } from 'react';

import { TagBox } from '@/components/common/TagBox';
import { TagSkeleton } from '@/components/common/TagSkeleton';

import * as Styled from './style';

interface Props {
  title: string;
  price: number;
  isExterirorColor?: boolean;
  tagId: number;
}

export function MyCarDescription({ title, price, isExterirorColor, tagId }: Props) {
  const currentPage = isExterirorColor ? 'exterior-color' : 'interior-color';

  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Title>
          {title}
          <Styled.SubTitle>에 대해 시승자들은 이런 후기를 남겼어요</Styled.SubTitle>
        </Styled.Title>
        <Styled.Price>+{price.toLocaleString()}원</Styled.Price>
      </Styled.Wrapper>
      <Styled.Line />
      <Styled.TagWrapper>
        <Suspense fallback={<TagSkeleton count={5} />}>
          <TagBox tagId={tagId} type={currentPage} limit={5} />
        </Suspense>
      </Styled.TagWrapper>
    </Styled.Container>
  );
}
