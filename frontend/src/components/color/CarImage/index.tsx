import React, { useState } from 'react';

import { RotateLogo } from '@/components/common/RotateLogo';
import { PrevButton } from '@/components/common/PrevButton';
import { NextButton } from '@/components/common/NextButton';

import * as style from './style';

interface CarImageProps {
  color: string;
}

export function CarImage({ color }: CarImageProps) {
  const [isClicked, setIsClicked] = useState(false);
  const [isRotate, setIsRotate] = useState(false);
  const [xPosition, setXPosition] = useState(0);
  const [currentImage, setCurrentImage] = useState(1);

  function handleClickRotateButton() {
    setIsRotate(true);
  }

  function handleClickPrevButton() {
    setCurrentImage(prev => (prev === 60 ? 1 : prev + 1));
  }

  function handleClickNextButton() {
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

  // TODO: 드래그할 때 마우스 클릭 떼도 유지되는 문제 수정
  function handleMouseMove(event: React.MouseEvent<HTMLImageElement>) {
    if (!isClicked || !isRotate) {
      return;
    }

    event.preventDefault();
    if (xPosition > event.screenX) {
      if (event.screenX % 2 === 0) {
        setCurrentImage(prev => (prev === 60 ? 1 : prev + 1));
      }
    } else {
      if (event.screenX % 2 === 0) {
        setCurrentImage(prev => (prev === 1 ? 60 : prev - 1));
      }
    }
  }

  return (
    <style.Container>
      <style.Wrapper>
        {isRotate && <PrevButton width="48" height="48" onClick={handleClickPrevButton} />}
        <style.CarImage
          onMouseDown={handleMouseDown}
          onMouseUp={handleMouseOver}
          onMouseMove={handleMouseMove}
          src={`https://www.hyundai.com/contents/vr360/LX06/exterior/${color}/0${currentImage
            .toString()
            .padStart(2, '0')}.png`}
          alt="VR 이미지"
        />
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
