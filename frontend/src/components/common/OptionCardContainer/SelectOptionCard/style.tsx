import styled from '@emotion/styled';
import { css } from '@emotion/react';

interface OptionCardProps {
  option: number;
  selectedOption: number;
}

export const Container = styled.div`
  width: 100%;
  height: 197px;
  margin-top: 24px;
  gap: 6px;

  display: flex;
`;

export const OptionCard = styled.div<OptionCardProps>`
  ${({ theme, selectedOption }) => {
    const { colors } = theme;

    return css`
      width: 160px;
      height: 197px;

      display: flex;
      flex-direction: column;
      overflow: hidden;

      border-radius: 6px;
      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const Image = styled.img`
  width: 160px;
  height: 93px;
`;

export const DescriptionWrapper = styled.div`
  width: 160px;
  height: 104px;
  padding: 10px 9px 8px 9px;
`;

export const Text = styled.p`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      colors: black;
      ${fonts.bodyMedium3}
    `;
  }}
`;

export const ButtonBox = styled.div`
  margin-top: 12px;

  display: flex;
  justify-content: center;
`;

export const Button = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      width: 142px;
      height: 30px;

      color: #385da2;
      ${fonts.bodyMedium3};
      text-align: center;

      border-radius: 4px;
      border: 1px solid #385da2;

      background-color: ${colors.hyundaiNeutral};
    `;
  }}
`;
