import { useEffect, useRef, useState } from 'react';

import { ReviewForm } from '@/components/Review/ReviewForm';
import { ReviewOption } from '@/components/Review/ReviewOption';
import { ReviewComplete } from '@/components/Review/ReviewComplete';
import { SlidePrevButton } from '@/components/Review/SlidePrevButton';
import { ReviewDescripion } from '@/components/Review/ReviewDescription';

import * as Styled from './style';

const TOTAL_SLIDES = 10;

export function Review() {
  const [currentSlide, setCurrentSlide] = useState(0);
  const slideRef = useRef<HTMLDivElement>(null);

  function handleNextSlide() {
    currentSlide <= TOTAL_SLIDES && setCurrentSlide(prev => prev + 1);
  }

  function handlePrevSlide() {
    currentSlide > 0 && setCurrentSlide(prev => prev - 1);
  }

  useEffect(() => {
    if (slideRef.current !== null) {
      slideRef.current.style.transition = 'all 0.4s ease';
      slideRef.current.style.transform = `translateX(-${currentSlide * 100}%)`;
    }
  }, [currentSlide]);

  const isButtonShow = currentSlide === 0 || currentSlide === TOTAL_SLIDES ? false : true;

  return (
    <Styled.Container>
      <Styled.SliderWrapper>
        {isButtonShow && (
          <Styled.PrevButton>
            <SlidePrevButton onClick={handlePrevSlide} />
          </Styled.PrevButton>
        )}
        <Styled.SliderBox ref={slideRef}>
          <Styled.Slide>
            <ReviewDescripion onClick={handleNextSlide} />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewOption
              name={'디젤 2.2'}
              description="높은 토크로 파워풀한 드라이빙이 가능하며, 차급대비 연비 효율이 우수합니다."
              tags={['주행을 편안하게 해요🚙', '뒷자석도 편안해요', '조용한 드라이빙😴', '부드러워요']}
              image="https://88hey-bucket.s3.ap-northeast-2.amazonaws.com/88hey/engine/dieselengine2.2.jpg"
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewOption
              name={'7인승'}
              description="기존 8인승 시트(1열 2명, 2열 3명, 3열 3명)에서 2열 가운데 시트를 없애 2열 탑승객의 편의는 물론, 3열 탑승객의 승하차가 편리합니다"
              tags={['주행을 편하게 해요', '뒷자석도 편안해요', '조용한 드라이빙']}
              image="https://88hey-bucket.s3.ap-northeast-2.amazonaws.com/88hey/body-type/7_seat.jpg"
              onClick={handleNextSlide}
            />
            https://88hey-bucket.s3.ap-northeast-2.amazonaws.com/88hey/wheel-drive/2wd.jpg
          </Styled.Slide>
          <Styled.Slide>
            <ReviewOption
              name={'2WD'}
              description="엔진에서 전달되는 동력이 전/후륜 바퀴 중 한쪽으로만 전달되어 차량을 움직이는 방식입니다 차체가 가벼워 연료 효율이 높습니다"
              tags={['주행을 편하게 해요', '뒷자석도 편안해요', '조용한 드라이빙']}
              image="https://88hey-bucket.s3.ap-northeast-2.amazonaws.com/88hey/wheel-drive/2wd.jpg"
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewOption
              name={'크리미 펄 화이트'}
              description="엔진에서 전달되는 동력이 전/후륜 바퀴 중 한쪽으로만 전달되어 차량을 움직이는 방식입니다 차체가 가벼워 연료 효율이 높습니다"
              tags={['주행을 편하게 해요', '뒷자석도 편안해요', '조용한 드라이빙']}
              image="https://88hey-bucket.s3.ap-northeast-2.amazonaws.com/88hey/color/exterior/car/whitepearl/image_001.webp"
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewOption
              name={'쿨 그레이'}
              description="엔진에서 전달되는 동력이 전/후륜 바퀴 중 한쪽으로만 전달되어 차량을 움직이는 방식입니다 차체가 가벼워 연료 효율이 높습니다"
              tags={['주행을 편하게 해요', '뒷자석도 편안해요', '조용한 드라이빙']}
              image="https://88hey-bucket.s3.ap-northeast-2.amazonaws.com/88hey/color/interior/car/coolgray.png"
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewForm
              tags={['주행을 편하게 해요', '뒷자석도 편안해요', '조용한 드라이빙']}
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewComplete />
          </Styled.Slide>
        </Styled.SliderBox>
      </Styled.SliderWrapper>
    </Styled.Container>
  );
}
