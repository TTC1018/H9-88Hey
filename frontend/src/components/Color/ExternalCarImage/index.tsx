import { MouseEvent, useState } from 'react';

import { convertToTwoDigits } from '@/utils';

import { RotateLogo } from '@/components/Color/RotateLogo';
import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';

import * as Styled from './style';

interface CarImageProps {
  imageUrl: string;
  current: number;
  setImageNum: (current: number) => void;
}

export function ExternalCarImage({ imageUrl, current, setImageNum }: CarImageProps) {
  const [isClicked, setIsClicked] = useState(false);
  const [isRotate, setIsRotate] = useState(false);
  const [xPosition, setXPosition] = useState(0);
  const [currentImage, setCurrentImage] = useState(0);

  const imageArray = generateImageArray(current);

  setImageNum(currentImage);

  function generateImageArray(num: number) {
    const array = [];
    for (let i = num; i <= num + 59; i++) {
      array.push(i > 59 ? i - 60 : i);
    }
    return array;
  }

  function handleClickRotateButton() {
    setIsRotate(true);
  }

  function handleClickPrevButton() {
    setCurrentImage(prev => (prev === 59 ? 0 : prev + 1));
  }

  function handleClickNextButton() {
    setCurrentImage(prev => (prev === 0 ? 59 : prev - 1));
  }

  function handleMouseDown(event: MouseEvent<HTMLDivElement>) {
    event.preventDefault();
    setIsClicked(true);
    setXPosition(event.screenX);
  }

  function handleMouseOver() {
    setIsClicked(false);
    setXPosition(0);
  }

  function handleMouseMove(event: MouseEvent<HTMLDivElement>) {
    if (!isClicked || !isRotate) {
      return;
    }

    event.preventDefault();
    if (xPosition > event.screenX + 5) {
      handleClickPrevButton();
      setXPosition(event.screenX);
    } else if (xPosition <= event.screenX - 5) {
      handleClickNextButton();
      setXPosition(event.screenX);
    }
  }

  function handleMouseLeave() {
    setIsClicked(false);
  }

  return (
    <Styled.Container>
      <Styled.Wrapper>
        {isRotate && <PrevButton width="48" height="48" onClick={handleClickPrevButton} />}
        <Styled.ImageBox
          onMouseDown={handleMouseDown}
          onMouseUp={handleMouseOver}
          onMouseMove={handleMouseMove}
          onMouseLeave={handleMouseLeave}
        >
          {imageUrl !== '' &&
            imageArray.map(num => {
              return (
                <Styled.Picture key={num}>
                  <Styled.CarSource
                    srcSet={`${imageUrl}image_0${convertToTwoDigits(num)}.webp`}
                    type="image/webp"
                    isDisplay={num === currentImage}
                  />
                  <Styled.CarImage
                    src={`${imageUrl}0${convertToTwoDigits(num)}.png`}
                    isDisplay={num === currentImage}
                    alt="VR 이미지"
                  />
                </Styled.Picture>
              );
            })}
        </Styled.ImageBox>
        {isRotate && <NextButton width="48" height="48" onClick={handleClickNextButton} />}
        {!isRotate && (
          <>
            <Styled.RotateBtn onClick={handleClickRotateButton}>
              360°
              <RotateLogo />
            </Styled.RotateBtn>
            <Styled.Vr360Circle>
              <Styled.Vr360Text>360°</Styled.Vr360Text>
            </Styled.Vr360Circle>
          </>
        )}
      </Styled.Wrapper>
    </Styled.Container>
  );
}
