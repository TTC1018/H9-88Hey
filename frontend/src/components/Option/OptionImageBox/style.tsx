import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const Container = styled.div`
  width: 479px;
  height: 304px;

  position: relative;
`;

export const Image = styled.img`
  width: 479px;
  height: 304px;

  opacity: 0;
  animation: fadeIn 0.5s ease-in-out forwards;
  @keyframes fadeIn {
    from {
      opacity: 0;
    }
    to {
      opacity: 1;
    }
  }
`;

export const Button = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      width: 98px;
      height: 29px;
      top: 16px;
      left: 366px;

      position: absolute;
      flex-shrink: 0;

      ${fonts.bodyRegular3};
      color: ${colors.hyundaiLightSand};
      text-align: center;

      background: ${colors.black};
      border-radius: 4px;
    `;
  }}
`;
