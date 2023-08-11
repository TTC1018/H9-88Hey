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
      color: ${isActive ? colors.black : colors.mediumGray};
      ${fonts.headingMedium4};
      text-decoration: ${isActive ? 'underline' : ''};
      text-decoration-thickness: ${isActive ? '3px' : 0};
    `;
  }}
`;
