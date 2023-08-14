import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface Props {
  isActive: boolean;
}
export const Container = styled.div<Props>`
  ${({ theme, isActive }) => {
    const { colors, fonts } = theme;
    return css`
      width: 354px;
      height: 56px;

      display: flex;
      justify-content: center;
      align-items: center;

      ${fonts.headingBold4}
      color:${isActive ? '#fff' : colors.darkGray};

      background-color: ${isActive ? colors.hyundaiPrimaryBlue : colors.lightGray};
      border-radius: 8px;
    `;
  }}
`;
