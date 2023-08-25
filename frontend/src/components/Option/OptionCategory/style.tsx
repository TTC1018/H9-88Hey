import styled from '@emotion/styled';
import { css } from '@emotion/react';

interface OptionProps {
  isActive: boolean;
}

export const OptionWrapper = styled.div`
  width: 1025px;
  height: 28px;
  gap: 23px;

  display: flex;
`;

export const Option = styled.button<OptionProps>`
  ${({ theme, isActive }) => {
    const { colors, fonts } = theme;

    return css`
      position: relative;

      color: ${isActive ? colors.black : colors.mediumGray};
      ${fonts.headingMedium4};

      &::after {
        height: ${isActive ? '5px' : '0px'};
        bottom: -2px;
        left: 0;
        right: 0;

        position: absolute;

        background-color: #000;

        transform-origin: center;
        ${isActive ? 'transform: scaleX(1)' : 'transform: scaleX(0)'};
        transition: transform 0.25s ease-out;

        content: '';
      }
      &:hover {
        color: ${colors.black};

        cursor: pointer;
      }
      &:hover:after {
        height: 5px;

        transform: scaleX(1);
        transform-origin: center;
      }
    `;
  }}
`;

interface Props {
  isActive: boolean;
}
export const Wrapper = styled.p<Props>`
  ${({ theme, isActive }) => {
    const { colors, fonts } = theme;
    return css`
      margin-bottom: 2px;

      position: relative;

      ${fonts.headingBold3}
      color: ${isActive ? colors.black : '#CECECE'};

      &::after {
        height: ${isActive ? '5px' : '0px'};
        bottom: -2px;
        left: 0;
        right: 0;

        position: absolute;

        background-color: #000;

        transform-origin: center;
        ${isActive ? 'transform: scaleX(1)' : 'transform: scaleX(0)'};
        transition: transform 0.25s ease-out;

        content: '';
      }

      &:hover {
        font-weight: bold;
        color: ${colors.black};

        cursor: pointer;
      }
      &:hover:after {
        height: 5px;

        transform: scaleX(1);
        transform-origin: center;
      }
    `;
  }}
`;
