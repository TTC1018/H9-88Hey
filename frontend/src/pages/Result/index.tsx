import { OptionCard } from '@/components/Result/OptionCard';

import * as style from './style';

const carPrice = 47340000;
const totalPrice = 50720000;

const mockData = [
  {
    imageUrl: 'https://www.hyundai.com/contents/spec/LX24/roa.jpg',
    name: '컴포트  ||',
    price: 1090000,
    subOptions: [
      '후석 승객 알림',
      '메탈 리어범퍼스텝',
      '메탈 도어스커프',
      '3열 파워폴딩시트',
      '3열 열선시트',
      '헤드업 디스플레이',
    ],
  },
  {
    imageUrl: 'https://www.hyundai.com/contents/spec/LX24/roa.jpg',
    name: '컴포트  ||',
    price: 1090000,
    subOptions: [
      '후석 승객 알림',
      '메탈 리어범퍼스텝',
      '메탈 도어스커프',
      '3열 파워폴딩시트',
      '3열 열선시트',
      '헤드업 디스플레이',
    ],
  },
  {
    imageUrl: 'https://www.hyundai.com/contents/spec/LX24/roa.jpg',
    name: '컴포트  ||',
    price: 1090000,
    subOptions: [
      '후석 승객 알림',
      '메탈 리어범퍼스텝',
      '메탈 도어스커프',
      '3열 파워폴딩시트',
      '3열 열선시트',
      '헤드업 디스플레이',
    ],
  },
  {
    imageUrl: 'https://www.hyundai.com/contents/spec/LX24/roa.jpg',
    name: '컴포트  ||',
    price: 1090000,
    subOptions: [
      '후석 승객 알림',
      '메탈 리어범퍼스텝',
      '메탈 도어스커프',
      '3열 파워폴딩시트',
      '3열 열선시트',
      '헤드업 디스플레이',
    ],
  },
  {
    imageUrl: 'https://www.hyundai.com/contents/spec/LX24/roa.jpg',
    name: '컴포트  ||',
    price: 1090000,
    subOptions: [
      '후석 승객 알림',
      '메탈 리어범퍼스텝',
      '메탈 도어스커프',
      '3열 파워폴딩시트',
      '3열 열선시트',
      '헤드업 디스플레이',
    ],
  },
  {
    imageUrl: 'https://www.hyundai.com/contents/spec/LX24/roa.jpg',
    name: '컴포트  ||',
    price: 1090000,
    subOptions: [
      '후석 승객 알림',
      '메탈 리어범퍼스텝',
      '메탈 도어스커프',
      '3열 파워폴딩시트',
      '3열 열선시트',
      '헤드업 디스플레이',
    ],
  },
];

export function Result() {
  return (
    <style.Container>
      <style.TitleWrapper>
        <style.TitleBox>
          <style.Title>PALISADE</style.Title>
          <style.TitleLine />
          <style.Message>나의 펠리세이드가 완성되었어요!</style.Message>
          <style.DescriptionBox>
            <style.Description>완성된 차량은 마이페이지</style.Description>
            <style.RightArrow src="/src/assets/icons/arrow_right.svg" />
            <style.Description>마이카이빙에서 볼 수 있어요</style.Description>
          </style.DescriptionBox>
        </style.TitleBox>
        <style.Image src="https://www.hyundai.com/contents/vr360/LX06/exterior/WC9/001.png" />
      </style.TitleWrapper>
      <style.LineWrapper>
        <style.TitleBoxLine />
      </style.LineWrapper>
      <style.SubTitleWrapper>
        <style.SubTitle>나의 펠리세이드는 이런 기능을 가지고 있어요</style.SubTitle>
      </style.SubTitleWrapper>
      <style.SummaryWrapper>
        <style.SummaryBox>
          <style.Name>펠리세이드 Le Blanc(르블랑)</style.Name>
          <style.DetailBox>
            <style.Trim>디젤 2.2 / 4WD / 7인승</style.Trim>
            <style.Price>{carPrice.toLocaleString()}원</style.Price>
          </style.DetailBox>
          <style.SummaryLine />
          <style.ColorBox>
            <style.ColorType>외장</style.ColorType>
            <style.Ellipse src="/src/assets/icons/ellipse_567.svg" />
            <style.ColorName>문라이트 블루펄</style.ColorName>
            <style.Space />
            <style.ColorType>내장</style.ColorType>
            <style.Ellipse src="/src/assets/icons/ellipse_123.png" />
            <style.ColorName>퀄팅 천연(블랙)</style.ColorName>
          </style.ColorBox>
        </style.SummaryBox>
      </style.SummaryWrapper>
      <style.OptionTitleWrapper>
        <style.OptionTitleBox>
          <style.OptionTitle>선택 옵션</style.OptionTitle>
          <style.OptionCount>6</style.OptionCount>
          <style.OptionUnit>개</style.OptionUnit>
        </style.OptionTitleBox>
      </style.OptionTitleWrapper>
      <style.OptionCardWrapper>
        <style.OptionCardBox>
          {mockData.map(({ imageUrl, name, price, subOptions }, index) => (
            <OptionCard imageUrl={imageUrl} name={name} price={price} subOptions={subOptions} index={index} />
          ))}
        </style.OptionCardBox>
      </style.OptionCardWrapper>
      <style.FooterLine />
      <style.MenuWrapper>
        <style.MenuBox>
          <style.ConsultMenuBox>
            <style.ConsultText>딜러에게 가까운 출고일자 상담을 받아볼까요?</style.ConsultText>
            <style.ConsultButton>
              출고일자 상담신청 바로가기
              <style.FooterRightArrow src="/src/assets/icons/arrow_right_2.svg" />
            </style.ConsultButton>
          </style.ConsultMenuBox>
          <style.PurchaseMenuBox>
            <style.ConsultText>구매를 위한 다른 절차가 필요하신가요?</style.ConsultText>
            <style.PurchaseButtonBox>
              <style.PurchaseButton>구매 상담 신청하기</style.PurchaseButton>
              <style.PurchaseButton>PDF 다운로드</style.PurchaseButton>
              <style.PurchaseButton>카탈로그 다운로드</style.PurchaseButton>
              <style.PurchaseButton>전시 차량 조회</style.PurchaseButton>
            </style.PurchaseButtonBox>
          </style.PurchaseMenuBox>
        </style.MenuBox>
      </style.MenuWrapper>
      <style.FooterContainer>
        <style.FooterWrapper>
          <style.FooterBox>
            <style.FooterPriceText>예상 견적 가격</style.FooterPriceText>
            <style.FooterPrice>{totalPrice.toLocaleString()}</style.FooterPrice>
            <style.FooterPriceUnitBox>
              <style.FooterPriceUnit>원</style.FooterPriceUnit>
            </style.FooterPriceUnitBox>
            <style.FooterButton>이 차량 구매하기</style.FooterButton>
          </style.FooterBox>
        </style.FooterWrapper>
      </style.FooterContainer>
    </style.Container>
  );
}
