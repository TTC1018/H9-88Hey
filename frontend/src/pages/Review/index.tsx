import { useEffect, useRef, useState } from 'react';

import { ReviewForm } from '@/components/Review/ReviewForm';
import { ReviewOption } from '@/components/Review/ReviewOption';
import { ReviewComplete } from '@/components/Review/ReviewComplete';
import { SlidePrevButton } from '@/components/Review/SlidePrevButton';
import { ReviewDescripion } from '@/components/Review/ReviewDescription';

import * as Styled from './style';

const TOTAL_SLIDES = 11;

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
              isBig={false}
              name={'디젤 2.2'}
              description="높은 토크로 파워풀한 드라이빙이 가능하며, 차급대비 연비 효율이 우수합니다."
              tags={['주행을 편안하게 해요🚙', '뒷자석도 편안해요', '조용한 드라이빙😴', '부드러워요']}
              image="https://88hey-bucket.s3.ap-northeast-2.amazonaws.com/88hey/engine/dieselengine2.2.jpg"
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewOption
              isBig={false}
              name={'7인승'}
              description="기존 8인승 시트(1열 2명, 2열 3명, 3열 3명)에서 2열 가운데 시트를 없애 2열 탑승객의 편의는 물론, 3열 탑승객의 승하차가 편리합니다"
              tags={['주행을 편하게 해요', '뒷자석도 편안해요', '조용한 드라이빙']}
              image="https://88hey-bucket.s3.ap-northeast-2.amazonaws.com/88hey/body-type/7_seat.jpg"
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewOption
              isBig={false}
              name={'2WD'}
              description="엔진에서 전달되는 동력이 전/후륜 바퀴 중 한쪽으로만 전달되어 차량을 움직이는 방식입니다 차체가 가벼워 연료 효율이 높습니다"
              tags={['주행을 편하게 해요', '뒷자석도 편안해요', '조용한 드라이빙']}
              image="https://88hey-bucket.s3.ap-northeast-2.amazonaws.com/88hey/wheel-drive/2wd.jpg"
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewOption
              isBig
              name={'크리미 펄 화이트'}
              tags={[
                '트렌디해요😎',
                '모두가 좋아하는 색상🥰',
                '깔끔해요👍',
                '흔하지 않은 색🤭',
                '밝고화사해요✨',
                '무게감있는 톤🌑',
              ]}
              image="https://88hey-bucket.s3.ap-northeast-2.amazonaws.com/88hey/color/exterior/car/whitepearl/image_001.webp"
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewOption
              isBig={false}
              name={'쿨 그레이'}
              tags={[
                '트렌디해요😎',
                '모두가 좋아하는 색상🥰',
                '깔끔해요👍',
                '흔하지 않은 색🤭',
                '밝고화사해요✨',
                '무게감있는 톤🌑',
              ]}
              image="https://88hey-bucket.s3.ap-northeast-2.amazonaws.com/88hey/color/interior/car/coolgray.png"
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewOption
              isBig={false}
              name={'컴포트 ||'}
              description="초음파 센서를 통해 뒷좌석에 남아있는 승객의 움직임을 감지하여 운전자에게 경고함으로써 부주의에 의한 유아 또는 반려 동물 등의 방치 사고를 예방하는 신기술입니다."
              tags={['주행을 편하게 해요', '뒷자석도 편안해요', '조용한 드라이빙']}
              image="https://88hey-bucket.s3.amazonaws.com/88hey/sub-option/roa.jpg"
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewOption
              isBig={false}
              name={'빌트인 공기청정기'}
              tags={[
                '가족들도 좋은 옵션👨‍👩‍👧',
                '여름에 쓰기 좋아요☀️',
                '개방감🪟',
                '트렌디한 디자인😎',
                '옵션값 뽑았어요👍',
                '멋진 하늘뷰☁️',
              ]}
              image="https://88hey-bucket.s3.amazonaws.com/88hey/select-option/builtinaircleaner.jpg"
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewOption
              isBig={false}
              name={'듀얼 머플러 패키지'}
              tags={[
                '가족들도 좋은 옵션👨‍👩‍👧',
                '여름에 쓰기 좋아요☀️',
                '트렌디한 디자인😎',
                '멋진 하늘뷰☁️',
                '편리해요☺️',
              ]}
              image="https://88hey-bucket.s3.amazonaws.com/88hey/select-option/dualmufflerpackage.jpg"
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewOption
              isBig={false}
              name={'20인치 다크 스퍼터링 휠'}
              tags={[
                '가족들도 좋은 옵션👨‍👩‍👧',
                '여름에 쓰기 좋아요☀️',
                '개방감🪟',
                '트렌디한 디자인😎',
                '옵션값 뽑았어요👍',
                '멋진 하늘뷰☁️',
              ]}
              image="https://88hey-bucket.s3.amazonaws.com/88hey/sub-option/20_darkwheel.jpg"
              onClick={handleNextSlide}
            />
          </Styled.Slide>
          <Styled.Slide>
            <ReviewForm
              tags={[
                '역시 풀옵션 없는 게 없어요👍',
                '외관이 멋져요🤩',
                '안전을 위한 옵션이 많아요🚨',
                '마음에 쏙 드는 색상💕',
                '부드러운 주행🪶',
                '가성비 최고! 필요한 건 다 있어요😎',
                '아이가 있는 가족👨‍👩‍👦‍👦',
                '사회초년생🧑‍🎓',
                '연비가 좋아요🛢',
                '출퇴근용으로 딱🚶',
                '드라이빙🚗',
                '흔하지 않은 옵션👀',
                '처음보는 옵션이 있었어요😳',
                '초보자에게 좋은 차',
                '반려동물🐶',
              ]}
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
