import React, { useState } from 'react';

import { RotateLogo } from '@/components/common/RotateLogo';
import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';

import * as style from './style';

export function CarImage() {
  const [isClicked, setIsClicked] = useState(false);
  const [isRotate, setIsRotate] = useState(false);
  const [xPosition, setXPosition] = useState(0);
  const [currentImage, setCurrentImage] = useState(1);

  function handleClickRotateBtn() {
    setIsRotate(true);
  }

  function handleClickPrevBtn() {
    setCurrentImage(prev => (prev === 60 ? 1 : prev + 1));
  }

  function handleClickNextBtn() {
    setCurrentImage(prev => (prev === 1 ? 60 : prev - 1));
  }

  function handleMouseDown(event: React.MouseEvent<HTMLImageElement>) {
    setIsClicked(true);
    setXPosition(event.screenX);
  }

  function handleMouseOver() {
    setIsClicked(false);
    setXPosition(0);
  }

  function handleMouseMove(event: React.MouseEvent<HTMLImageElement>) {
    if (!isClicked || !isRotate) {
      return;
    }

    event.preventDefault();
    if (xPosition > event.screenX) {
      if (event.screenX % 5 === 0) {
        setCurrentImage(prev => (prev === 60 ? 1 : prev + 1));
      }
    } else {
      if (event.screenX % 5 === 0) {
        setCurrentImage(prev => (prev === 1 ? 60 : prev - 1));
      }
    }
  }

  return (
    <style.Container>
      <style.Wrapper>
        {currentImage}
        {isRotate && <PrevButton width="48" height="48" onClick={handleClickPrevBtn} />}
        <style.CarImage
          onMouseDown={handleMouseDown}
          onMouseUp={handleMouseOver}
          onMouseMove={handleMouseMove}
          src={`src/assets/abyss_exterior(accel)/image_0${currentImage.toString().padStart(2, '0')}.png`}
        />
        {isRotate && <NextButton width="48" height="48" onClick={handleClickNextBtn} />}
        {!isRotate && (
          <>
            <style.rotateBtn onClick={handleClickRotateBtn}>
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
