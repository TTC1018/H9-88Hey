import React, { useState } from 'react';

import { RotateLogo } from '@/components/common/RotateLogo';
import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';

import * as style from './style';

interface CarImageProps {
  color: string;
}

export function ExternalCarImage({ color }: CarImageProps) {
  const [isClicked, setIsClicked] = useState(false);
  const [isRotate, setIsRotate] = useState(false);
  const [xPosition, setXPosition] = useState(0);
  const [currentImage, setCurrentImage] = useState(1);

  const imageArray = Array.from({ length: 60 }, (_, index) => index + 1);

  function handleClickRotateButton() {
    setIsRotate(true);
  }

  function handleClickPrevButton() {
    setCurrentImage(prev => (prev === 60 ? 1 : prev + 1));
  }

  function handleClickNextButton() {
    setCurrentImage(prev => (prev === 1 ? 60 : prev - 1));
  }

  function handleMouseDown(event: React.MouseEvent<HTMLDivElement>) {
    event.preventDefault();
    setIsClicked(true);
    setXPosition(event.screenX);
  }

  function handleMouseOver() {
    setIsClicked(false);
    setXPosition(0);
  }

  function handleMouseMove(event: React.MouseEvent<HTMLDivElement>) {
    if (!isClicked || !isRotate) {
      return;
    }

    event.preventDefault();
    if (xPosition > event.screenX) {
      if (event.screenX % 2 === 0) {
        setCurrentImage(prev => (prev === 60 ? 1 : prev + 1));
        setXPosition(event.screenX);
      }
    } else {
      if (event.screenX % 2 === 0) {
        setCurrentImage(prev => (prev === 1 ? 60 : prev - 1));
        setXPosition(event.screenX);
      }
    }
  }

  function handleMouseLeave() {
    setIsClicked(false);
  }

  return (
    <style.Container>
      <style.Wrapper>
        {isRotate && <PrevButton width="48" height="48" onClick={handleClickPrevButton} />}
        <style.ImageBox
          onMouseDown={handleMouseDown}
          onMouseUp={handleMouseOver}
          onMouseMove={handleMouseMove}
          onMouseLeave={handleMouseLeave}
        >
          {imageArray.map(num => (
            <style.CarImage
              key={num}
              isDisplay={num === currentImage}
              src={`https://www.hyundai.com/contents/vr360/LX06/exterior/${color}/0${num
                .toString()
                .padStart(2, '0')}.png`}
              alt="VR 이미지"
            />
          ))}
        </style.ImageBox>
        {isRotate && <NextButton width="48" height="48" onClick={handleClickNextButton} />}
        {!isRotate && (
          <>
            <style.rotateBtn onClick={handleClickRotateButton}>
              360°
              <RotateLogo />
            </style.rotateBtn>
            <style.vr360Circle>
              <style.vr360Text>360°</style.vr360Text>
            </style.vr360Circle>
          </>
        )}
      </style.Wrapper>
    </style.Container>
  );
}
