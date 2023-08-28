import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 100vw;
  top: 0;
  padding: 20px 0 0 0;

  display: flex;
  flex-direction: column;
  align-items: center;
  position: sticky;

  background-color: #fff;

  z-index: 2;
`;

export const Wrapper = styled.div`
  width: 1024px;
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
      margin: 8px 0 0 0;

      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;
