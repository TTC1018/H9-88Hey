import { ReviewButton } from '@/components/review/ReviewButton';
import { ReviewTag } from '@/components/review/ReviewTag';
import { ReviewTextArea } from '@/components/review/ReviewTextArea';

import * as Styled from './style';

interface Props {
  image: string;
  name: string;
  description: string;
  tags: string[];
  onClick: () => void;
}
export function ReviewOption({ name, description, tags, image, onClick }: Props) {
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
          <ReviewTag tags={tags} />
          <ReviewTextArea />
          <ReviewButton text={'다음'} onClick={onClick} isActive />
        </Styled.TagBox>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
