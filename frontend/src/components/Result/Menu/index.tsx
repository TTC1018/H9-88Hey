import * as Styled from './style';

export function Menu() {
  return (
    <Styled.Container>
      <Styled.MenuWrapper>
        <Styled.ConsultMenuWrapper>
          <Styled.ConsultText>딜러에게 가까운 출고일자 상담을 받아볼까요?</Styled.ConsultText>
          <Styled.ConsultButton>
            출고일자 상담신청 바로가기
            <Styled.FooterRightArrow src="/public/assets/icons/arrow_right_2.svg" />
          </Styled.ConsultButton>
        </Styled.ConsultMenuWrapper>
        <Styled.PurchaseMenuWrapper>
          <Styled.ConsultText>구매를 위한 다른 절차가 필요하신가요?</Styled.ConsultText>
          <Styled.PurchaseButtonBox>
            <Styled.PurchaseButton>구매 상담 신청하기</Styled.PurchaseButton>
            <Styled.PurchaseButton>PDF 다운로드</Styled.PurchaseButton>
            <Styled.PurchaseButton>카탈로그 다운로드</Styled.PurchaseButton>
            <Styled.PurchaseButton>전시 차량 조회</Styled.PurchaseButton>
          </Styled.PurchaseButtonBox>
        </Styled.PurchaseMenuWrapper>
      </Styled.MenuWrapper>
    </Styled.Container>
  );
}
