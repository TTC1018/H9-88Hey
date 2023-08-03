import styled from '@emotion/styled';
import { css } from '@emotion/react';

interface OptionProps {
  option: number;
  selectedCategory: number;
  onClick: (option: number) => void;
}

export const OptionWrapper = styled.div`
  width: 1025px;
  height: 28px;
  gap: 23px;

  display: flex;
`;

export const Option = styled.button<OptionProps>`
  ${({ theme, option, selectedCategory }) => {
    const { colors, fonts } = theme;

    return css`
      color: ${option === selectedCategory ? colors.black : colors.mediumGray};
      ${fonts.headingMedium4};
      text-decoration: ${option === selectedCategory ? 'underline' : ''};
      text-decoration-thickness: ${option === selectedCategory ? '3px' : 0};
    `;
  }}
`;
