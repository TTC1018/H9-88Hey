import styled from '@emotion/styled';
import { css } from '@emotion/react';

interface CategoryProps {
  category: number;
  selectedCategory: number;
}

export const Container = styled.div`
  width: 990px;
  height: 200px;
  gap: 6px;
  margin-top: 18px;

  display: flex;
  flex-direction: column;
`;

export const CategoryWrapper = styled.div`
  display: flex;
  gap: 20px;
`;

export const Category = styled.p<CategoryProps>`
  ${({ theme, category, selectedCategory }) => {
    const { colors, fonts } = theme;

    return css`
      color: ${category === selectedCategory ? colors.black : colors.mediumGray};
      ${fonts.bodyMedium4};
    `;
  }}
`;

export const OptionCardWrapper = styled.div`
  width: 990px;
  height: 162px;

  display: flex;
  gap: 6px;
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
      color: ${colors.black};
      ${fonts.bodyMedium3};
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
