import styled from '@emotion/styled';
import { css } from '@emotion/react';

interface CategoryProps {
  isActive: boolean;
}

export const Container = styled.div`
  width: 990px;
  height: 197px;
  gap: 6px;
  margin-top: 18px;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

export const CategoryWrapper = styled.div`
  gap: 20px;

  display: flex;
`;

export const Category = styled.button<CategoryProps>`
  ${({ theme, isActive }) => {
    const { colors, fonts } = theme;

    return css`
      color: ${isActive ? colors.black : colors.mediumGray};
      ${fonts.bodyMedium4};
    `;
  }}
`;

export const OptionCardWrapper = styled.div`
  width: 1098px;
  height: 162px;
  gap: 6px;

  display: flex;

  transform: translateX(-54px);
`;

export const OptionCard = styled.div`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 160px;
      height: 162px;

      display: flex;
      flex-direction: column;
      overflow: hidden;

      border-radius: 8px;
      background-color: ${colors.hyundaiLightSand};
      cursor: pointer;
    `;
  }}
`;

export const Image = styled.img`
  width: 160px;
  height: 100px;
`;

export const TextWrapper = styled.div`
  width: 160px;
  height: 62px;
  padding: 8px 6px 0 16px;

  display: flex;
  flex-direction: column;
`;

export const Text = styled.p`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      overflow: hidden;

      color: ${colors.black};
      ${fonts.bodyMedium3};
      text-overflow: ellipsis;
      white-space: nowrap;
    `;
  }}
`;

export const SubText = styled.p`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      color: ${colors.mediumGray};
      ${fonts.bodyMedium3};
    `;
  }}
`;
