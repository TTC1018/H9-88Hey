import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 354px;

  display: flex;
  flex-direction: column;
`;

export const TextWrapper = styled.div`
  gap: 4px;
  margin-bottom: 10px;

  display: flex;
  align-items: center;
`;
export const TagWrapper = styled.div`
  gap: 8px;

  display: flex;
  flex-wrap: wrap;
`;

export const Medium = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium2};
    `;
  }}
`;

export const Regular = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.bodyRegular4};
      color: ${colors.mediumGray};
    `;
  }}
`;

interface Props {
  isActive: boolean;
}
export const Tag = styled.div<Props>`
  ${({ theme, isActive }) => {
    const { fonts, colors } = theme;
    return css`
      padding: 4px 8px;

      display: inline-flex;
      justify-content: center;
      align-items: center;

      ${fonts.bodyRegular3};
      color: ${isActive ? colors.hyundaiNeutral : colors.black};

      background-color: ${isActive ? colors.black : colors.hyundaiLightSand};
      border-radius: 8px;

      cursor: pointer;
    `;
  }}
`;
