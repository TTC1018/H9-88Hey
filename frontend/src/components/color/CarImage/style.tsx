import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div``;

export const Wrapper = styled.div`
  width: 700px;

  display: flex;
  align-items: center;
  justify-content: center;
  position: relative;
`;

interface CarImageProps {
  isDisplay: boolean;
}

export const CarImage = styled.img<CarImageProps>`
  ${({ isDisplay }) => {
    return css`
      width: 603px;
      display: ${isDisplay ? 'block' : 'none'};
    `;
  }}
`;

export const rotateBtn = styled.button`
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
      transform: translate(-50%, -50%);

      ${fonts.headingMedium4}

      background-color: rgba(246, 243, 242, 0.6);
      border: 1px solid ${colors.hyundaiSand};
      border-radius: 50%;
    `;
  }}
`;

export const vr360Circle = styled.div`
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

export const vr360Text = styled.span`
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
