import { useState } from 'react';

import { ReviewTag } from '@/components/Review/ReviewTag';
import { ReviewButton } from '@/components/Review/ReviewButton';
import { ReviewTextArea } from '@/components/Review/ReviewTextArea';

import * as Styled from './style';

interface Props {
  image: string;
  name: string;
  description: string;
  tags: string[];
  onClick: () => void;
}
export function ReviewOption({ name, description, tags, image, onClick }: Props) {
  const [selectedTags, setSelectedTags] = useState<Set<string>>(new Set());

  function handleSelectTag(tag: string) {
    setSelectedTags(prev => {
      if (prev.has(tag)) {
        const newSet = new Set(prev);
        newSet.delete(tag);

        return newSet;
      }

      if (prev.size === 3) {
        return prev;
      }

      return new Set([...prev, tag]);
    });
  }

  return (
    <Styled.Container>
      <Styled.Title>
        <Styled.Focus>{name}</Styled.Focus>은 어땠나요?
      </Styled.Title>
      <Styled.Wrapper>
        <Styled.ImageBox>
          <Styled.Image src={image} alt="image" />
          <Styled.SubTitle>{name}</Styled.SubTitle>
          <Styled.Line />
          <Styled.Description>{description}</Styled.Description>
        </Styled.ImageBox>
        <Styled.TagBox>
          <ReviewTag tags={tags} selectedIndex={selectedTags} onClick={handleSelectTag} />
          <ReviewTextArea />
          <ReviewButton text={'다음'} onClick={onClick} isActive={selectedTags.size > 0} />
        </Styled.TagBox>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
