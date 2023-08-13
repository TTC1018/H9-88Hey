import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const Container = styled.div`
  width: 100%;

  display: flex;
  flex-direction: column;
  aign-items: center;
`;

export const FooterLine = styled.hr`
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
