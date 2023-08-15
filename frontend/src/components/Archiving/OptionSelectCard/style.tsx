import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface Props {
  isActive: boolean;
  isArchiving: boolean;
}
export const Container = styled.div<Props>`
  ${({ theme, isActive, isArchiving }) => {
    const { fonts, colors } = theme;
    return css`
      height: 24px;
      padding: 0 10px;

      display: block;

      ${fonts.bodyRegular3}
      color:${isActive ? colors.hyundaiLightSand : colors.darkGray};
      white-space: nowrap;

      background-color: ${isArchiving ? (isActive ? '#385DA2' : colors.hyundaiNeutral) : colors.hyundaiSand};
      border: 0.5px solid ${isActive ? '#385DA2' : colors.lightGray};
      border-radius: 2px;

      cursor: pointer;
    `;
  }}
`;
