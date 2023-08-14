import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.header`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 100%;

      display: flex;
      align-items: center;
      justify-content: center;

      background-color: ${colors.hyundaiSand};
    `;
  }}
`;

export const Wrapper = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 100%;
      height: 60px;
      max-width: 1280px;

      display: flex;
      justify-content: space-between;
      align-items: center;

      background-color: ${colors.hyundaiSand};
    `;
  }}
`;

export const Box = styled.div`
  gap: 10px;

  display: flex;
  align-items: center;
`;

export const Division = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 1px;
      height: 50%;

      background-color: ${colors.black};
    `;
  }}
`;

export const Text = styled.h1`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium4};
    `;
  }}
`;

export const CarNameText = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      ${fonts.headingBold4};
      color: ${colors.black};
    `;
  }}
`;

export const Button = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      padding: 4px 8px;

      ${fonts.bodyMedium3};
      color: ${colors.hyundaiNeutral};

      background-color: ${colors.darkGray};
      border-radius: 4px;
    `;
  }}
`;
