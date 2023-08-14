import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div``;

export const Wrapper = styled.div`
  gap: 25px;

  display: flex;
`;

interface TitleTextProps {
  isActive: boolean;
}

export const TitleText = styled.span<TitleTextProps>`
  ${({ theme, isActive }) => {
    const { colors, fonts } = theme;
    return css`
      position: relative;

      ${fonts.headingMedium3};
      color: ${isActive ? colors.black : '#CECECE'};

      cursor: pointer;

      &::after {
        width: 100%;
        height: 6px;
        left: 0;
        bottom: -14px;

        position: absolute;

        background-color: ${isActive ? colors.black : colors.hyundaiLightSand};

        content: '';
      }
    `;
  }}
`;

export const Division = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 100%;
      height: 6px;
      margin: 8px 0 25px 0;

      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;
