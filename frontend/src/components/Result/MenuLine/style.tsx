import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const MenuLine = styled.hr`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 100%;
      height: 18px;
      margin: 42px 0 0 0;

      border: 0;
      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;
