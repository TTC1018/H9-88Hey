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
        height: ${isActive ? '6px' : '0px'};
        left: 0;
        bottom: -14px;

        position: absolute;

        background-color: ${colors.black};

        transform-origin: center;
        transform: ${isActive ? 'scaleX(1)' : 'scaleX(0)'};
        transition: transform 0.25s ease-out;

        content: '';
      }

      &:hover {
        font-weight: bold;
        color: ${colors.black};

        cursor: pointer;
      }
      &:hover:after {
        height: 6px;

        transform: scaleX(1);
        transform-origin: center;
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
