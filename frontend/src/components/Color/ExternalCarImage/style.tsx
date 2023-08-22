import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface CarImageProps {
  isDisplay: boolean;
  isLoaded: boolean;
}

export const Container = styled.div`
  width: 644px;
  height: 401px;

  display: flex;
  align-items: center;
  justify-content: center;
`;

export const Wrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
`;

export const ImageBox = styled.div``;

export const CarImage = styled.img<CarImageProps>`
  ${({ isDisplay, isLoaded }) => {
    return css`
      width: 603px;

      display: ${isDisplay ? 'block' : 'none'};

      filter: ${isLoaded ? 'none' : 'blur(6px)'};
      transition: filter 0.5s ease-in-out;
    `;
  }}
`;

export const LoaderSpinner = styled.div`
  width: 70px;
  height: 70px;
  top: 50%;
  left: 50%;

  position: absolute;

  background-color: transparent;
  border-radius: 50%;
  border: 2px solid #fff;

  transform: translate(-50%, -50%);
`;

export const RotateBtn = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      width: 75px;
      height: 75px;
      top: 50%;
      left: 50%;

      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      position: absolute;

      ${fonts.headingMedium4}

      background-color: rgba(246, 243, 242, 0.6);
      border: 1px solid ${colors.hyundaiSand};
      border-radius: 50%;

      transform: translate(-50%, -50%);
    `;
  }}
`;

export const Vr360Circle = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 546.721px;
      height: 76.066px;
      bottom: 35px;
      left: 47%;
      z-index: -1;

      position: absolute;
      transform: translateX(-50%);

      background: ${colors.hyundaiLightSand};
      border: 1px solid ${colors.hyundaiSand};
      border-radius: 50%;
    `;
  }}
`;

export const Vr360Text = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      bottom: -14px;
      left: 50%;
      padding: 0 3px;

      position: absolute;
      transform: translateX(-50%);

      ${fonts.headingMedium4}
      color: ${colors.darkGray}
      
      background-color: #fff;
      border-radius: 10px;
    `;
  }}
`;
