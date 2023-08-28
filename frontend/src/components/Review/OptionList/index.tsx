import * as Styled from './style';

export function OptionList() {
  return (
    <Styled.Container>
      <Styled.Wrapper>
        <Styled.Image src={'https://www.hyundai.com/contents/spec/LX24/roa_s.jpg'} />
        <Styled.TextBox>
          <Styled.Encloser>
            <Styled.Medium>컴포트 || </Styled.Medium>
            <Styled.Price>1,090,000원</Styled.Price>
          </Styled.Encloser>
          <Styled.Description>
            후석 승객 알림 / 메탈 리어범퍼스텝 / 메탈 도어스커프 / 3열 파워폴딩시트 / 3열 열선시트 / 헤드업 디스틀레이
          </Styled.Description>
        </Styled.TextBox>
      </Styled.Wrapper>
      <Styled.Wrapper>
        <Styled.Image src={'https://www.hyundai.com/contents/spec/LX24/builtinaircleaner_s.jpg'} />
        <Styled.TextBox>
          <Styled.Encloser>
            <Styled.Medium>빌트인 공기청정기</Styled.Medium>
            <Styled.Price>400,000원</Styled.Price>
          </Styled.Encloser>
        </Styled.TextBox>
      </Styled.Wrapper>
      <Styled.Wrapper>
        <Styled.Image src={'https://www.hyundai.com/contents/spec/LX24/kneewarmer_s.jpg'} />
        <Styled.TextBox>
          <Styled.Encloser>
            <Styled.Medium>적외선 무릎워머</Styled.Medium>
            <Styled.Price>300,000원</Styled.Price>
          </Styled.Encloser>
        </Styled.TextBox>
      </Styled.Wrapper>
      <Styled.Wrapper>
        <Styled.Image src={'https://www.hyundai.com/contents/spec/LX24/carprotector_s.jpg'} />
        <Styled.TextBox>
          <Styled.Encloser>
            <Styled.Medium>차량 보호 필름</Styled.Medium>
            <Styled.Price>490,000원</Styled.Price>
          </Styled.Encloser>
        </Styled.TextBox>
      </Styled.Wrapper>
      <Styled.Wrapper>
        <Styled.Image src={'https://www.hyundai.com/contents/spec/LX24/20_darkwheel_s.jpg'} />
        <Styled.TextBox>
          <Styled.Encloser>
            <Styled.Medium>20인치 다크 스퍼터링 휠</Styled.Medium>
            <Styled.Price>840,000원</Styled.Price>
          </Styled.Encloser>
        </Styled.TextBox>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
