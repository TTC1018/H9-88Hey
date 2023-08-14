import { useEffect, useRef, useState } from 'react';

import { ReviewForm } from '@/components/Review/ReviewForm';
import { ReviewOption } from '@/components/Review/ReviewOption';
import { ReviewComplete } from '@/components/Review/ReviewComplete';
import { SlidePrevButton } from '@/components/Review/SlidePrevButton';
import { ReviewDescripion } from '@/components/Review/ReviewDescription';

import * as Styled from './style';

const TOTAL_SLIDES = 4; // 나중엔 데이터 보고 할거

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
              tags={['주행을 편하게 해요', '뒷자석도 편안해요', '조용한 드라이빙']}
              image="https://www.hyundai.com/contents/spec/LX24/20_darkwheel_s.jpg"
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewOption
              name={'디젤 2.2'}
              description="높은 토크로 파워풀한 드라이빙이 가능하며, 차급대비 연비 효율이 우수합니다."
              tags={['주행을 편하게 해요', '뒷자석도 편안해요', '조용한 드라이빙']}
              image="https://www.hyundai.com/contents/spec/LX24/20_darkwheel_s.jpg"
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
