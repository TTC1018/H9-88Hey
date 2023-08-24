import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface Props {
  isActive: boolean;
}
export const Container = styled.svg<Props>`
  ${({ theme, isActive }) => {
    const { colors } = theme;
    return css`
      width: 52px;
      height: 52px;

      cursor: pointer;
      fill: ${isActive ? colors.hyundaiPrimaryBlue : '#E4DCD3'};
      &:hover {
        fill: '#9baed0';
      }
    `;
  }}
`;
