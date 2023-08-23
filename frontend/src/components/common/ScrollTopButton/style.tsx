import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const Container = styled.button`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 50px;
      height: 50px;
      bottom: 30px;
      right: 30px;

      position: fixed;
      display: flex;
      justify-content: center;
      align-items: center;

      background-color: ${colors.hyundaiSand};
      border-radius: 8px;
    `;
  }}
`;

export const Image = styled.img``;
