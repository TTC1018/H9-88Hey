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
      width: 74px;
      padding: 4px 8px;

      ${fonts.bodyMedium3};
      color: ${colors.hyundaiNeutral};
      white-space: nowrap;

      background-color: ${colors.darkGray};
      border-radius: 4px;
    `;
  }}
`;

export const LogoutButton = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      width: 101.32px;
      height: 37px;

      color: ${colors.hyundaiNeutral};
      ${fonts.headingBold4};

      border-radius: 8px;
      background-color: ${colors.hyundaiPrimaryBlue};

      transform: scale(0.7);
    `;
  }}
`;

export const UserNameText = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      ${fonts.headingBold4};
    `;
  }}
`;

export const GreetingText = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      ${fonts.bodyRegular2};
    `;
  }}
`;
