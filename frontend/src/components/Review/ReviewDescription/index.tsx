import { OptionList } from '@/components/Review/OptionList';
import { ReviewImageCard } from '@/components/Review/ReviewImageCard';
import { ReviewButton } from '@/components/Review/ReviewButton';

import * as Styled from './style';

interface Props {
  onClick: () => void;
}
export function ReviewDescripion({ onClick }: Props) {
  return (
    <Styled.Container>
      <Styled.Title>
        <Styled.Regular>
          오늘 시승한 차량은 <Styled.Focus>팰리세이드 Le Blanc</Styled.Focus> 이에요.
        </Styled.Regular>
        <Styled.Regular>시승한 차량 정보를 가져왔어요.</Styled.Regular>
      </Styled.Title>
      <Styled.Wrapper>
        <ReviewImageCard
          image="https://www.hyundai.com/contents/vr360/LX06/exterior/A2B/001.png"
          title="팰리세이드 Le Blanc(르블랑)"
          subTitle="디젤 2.2 / 4WD / 7인승"
          price={47340000}
          interiorColor="문라이트 블루펄"
          exteriorColor="퀄팅 천연(블랙)"
        />
        <Styled.OptionBox>
          옵션 요약보기
          <OptionList />
        </Styled.OptionBox>
      </Styled.Wrapper>
      <ReviewButton onClick={onClick} text={'후기 작성 시작하기'} isActive />
    </Styled.Container>
  );
}
