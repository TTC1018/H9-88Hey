import * as style from './style';

export function Footer() {
  return (
    <style.Container>
      <style.TrimWrapper>
        <style.Title>트림</style.Title>
        <style.CarName>Le Blanc</style.CarName>
        <style.TrimDetail>디젤 2.2/7인승/4WD</style.TrimDetail>
      </style.TrimWrapper>
      <style.Division />
      <style.ColorWrapper>
        <style.Title>선택 색상</style.Title>
        <style.ColorBox>
          <style.ColorTitle>외장</style.ColorTitle>
          <style.ColorName>
            <style.ColorCircle />
            <style.ColorNameText>문라이트 블루펄</style.ColorNameText>
          </style.ColorName>
        </style.ColorBox>
        <style.ColorBox>
          <style.ColorTitle>내장</style.ColorTitle>
          <style.ColorName>
            <style.ColorCircle />
            <style.ColorNameText>퀄팅 천연 (블랙)</style.ColorNameText>
          </style.ColorName>
        </style.ColorBox>
      </style.ColorWrapper>
      <style.Division />
      <style.OptionWrapper>
        <style.Title>선택 옵션</style.Title>
        <style.OptionBox>
          <style.Option>2열 통풍시트</style.Option>
          <style.Option>빌트인 공기청정기</style.Option>
          <style.Option>빌트인 캠(보조)</style.Option>
          <style.Option>+2</style.Option>
        </style.OptionBox>
      </style.OptionWrapper>
      <style.Division />
      <style.PriceWrapper>
        <style.Title>예상 가격</style.Title>
        <style.PriceText>
          47,720,000 <style.PriceUnitText>원</style.PriceUnitText>
        </style.PriceText>
      </style.PriceWrapper>
      <style.ButtonWrapper>
        <style.PrevButton>이전</style.PrevButton>
        <style.NextButton>다음</style.NextButton>
      </style.ButtonWrapper>
    </style.Container>
  );
}
