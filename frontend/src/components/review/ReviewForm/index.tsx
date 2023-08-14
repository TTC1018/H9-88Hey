import { ReviewButton } from '@/components/Review/ReviewButton';
import { ReviewTag } from '@/components/Review/ReviewTag';
import { ReviewTextArea } from '@/components/Review/ReviewTextArea';

import * as Styled from './style';

interface Props {
  tags: string[];
  onClick: () => void;
}
export function ReviewForm({ tags, onClick }: Props) {
  return (
    <Styled.Container>
      <Styled.TitleWrapper>
        <Styled.Title>
          마지막으로,
          <br />
          차량의 어떤 점이 가장 인상깊었나요?
        </Styled.Title>
      </Styled.TitleWrapper>
      <Styled.Wrapper>
        <Styled.TagBox>
          <ReviewTag tags={tags} />
        </Styled.TagBox>
        <Styled.TextBox>
          <ReviewTextArea />
          <ReviewButton text={'후기 작성 완료하기'} onClick={onClick} isActive />
        </Styled.TextBox>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
