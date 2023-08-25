import { css } from '@emotion/react';
import styled from '@emotion/styled';

const Container = styled.header`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 100%;

      display: flex;
      align-items: center;
      justify-content: center;
      z-index: 1;

      background-color: ${colors.hyundaiSand};
    `;
  }}
`;

const Wrapper = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 100%;
      max-width: 1280px;
      padding: 15px 0;

      display: flex;
      justify-content: space-between;
      align-items: center;

      background-color: ${colors.hyundaiSand};
    `;
  }}
`;

const Box = styled.div`
  gap: 10px;

  display: flex;
  align-items: center;
`;

const Division = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 1px;
      height: 50%;

      background-color: ${colors.black};
    `;
  }}
`;

const Text = styled.h1`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium4};
    `;
  }}
`;

const ButtonWrapper = styled.div`
  display: flex;
  align-items: center;
  gap: 1px;
`;

const InfoBox = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;

interface AutoSavingBoxProps {
  isDisplay: boolean;
}

const AutoSavingBox = styled.div<AutoSavingBoxProps>`
  ${({ isDisplay }) => {
    return css`
      gap: 5px;

      display: flex;
      align-items: center;

      animation: ${isDisplay ? 'showAutoSaving 0.5s linear forwards' : 'hideAutoSaving 0.5s linear forwards'};

      @keyframes showAutoSaving {
        0% {
          opacity: 0;
        }
        100% {
          opacity: 1;
        }
      }

      @keyframes hideAutoSaving {
        0% {
          opacity: 1;
        }
        100% {
          opacity: 0;
        }
      }
    `;
  }}
`;

const AutoSavingText = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium4};
    `;
  }}
`;

const CarNameText = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      color: ${colors.black};

      ${fonts.headingBold4}
    `;
  }}
`;

const ButtonBox = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      padding: 7px 15px;
      gap: 5px;

      display: flex;
      align-items: center;

      color: ${colors.hyundaiSand};

      background-color: ${colors.black};
      border-radius: 80px;

      cursor: pointer;
    `;
  }}
`;

const ButtonText = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium4};
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

export {
  Container,
  Wrapper,
  Box,
  Division,
  Text,
  ButtonWrapper,
  InfoBox,
  AutoSavingBox,
  AutoSavingText,
  CarNameText,
  ButtonBox,
  ButtonText,
};
