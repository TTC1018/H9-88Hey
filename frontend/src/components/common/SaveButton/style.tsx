import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.svg`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 52px;
      height: 52px;

      cursor: pointer;

      &:hover {
        fill: ${colors.hyundaiGold};
      }
    `;
  }}
`;
