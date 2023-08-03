import styled from '@emotion/styled';
import { css } from '@emotion/react';

interface OptionProps {
  option: number;
  selectedOption: number;
  onClick: (option: number) => void;
}

export const OptionWrapper = styled.div`
  width: 1025px;
  height: 28px;
  gap: 23px;

  display: flex;
`;

export const Option = styled.button<OptionProps>`
  ${({ theme, option, selectedOption }) => {
    const { colors, fonts } = theme;

    return css`
      color: ${option === selectedOption ? colors.black : colors.mediumGray};
      ${fonts.headingMedium4};
      text-decoration: ${option === selectedOption ? 'underline' : ''};
      text-decoration-thickness: ${option === selectedOption ? '3px' : 0};
    `;
  }}
`;
