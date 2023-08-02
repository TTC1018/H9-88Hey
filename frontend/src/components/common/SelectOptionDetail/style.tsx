import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const Container = styled.div`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 507px;
      height: 152px;

      display: flex;

      border-radius: 8px;
      border: 2px solid ${colors.hyundaiPrimaryBlue};
      background: rgba(0, 44, 95, 0.1);
    `;
  }}
`;

export const TitleWrapper = styled.div`
  width: 507px;
  height: 152px;

  display: flex;
  flex-direction: column;
`;

export const TitleBox = styled.div`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      color: ${colors.hyundaiPrimaryBlue};
      ${fonts.headingMedium4}
    `;
  }}
`;
