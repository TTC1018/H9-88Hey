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
  gap: 15px;
`;

const InfoBox = styled.div`
  display: flex;
  align-items: center;
  gap: 25px;
`;

interface AutoSavingBoxProps {
  isDisplay: boolean;
}

const AutoSavingBox = styled.div<AutoSavingBoxProps>`
  ${({ isDisplay }) => {
    return css`
      display: ${isDisplay ? 'flex' : 'none'};
      align-items: center;
      gap: 5px;

      animation: showAutoSaving 0.2s linear;

      @keyframes showAutoSaving {
        0% {
          transform: translateY(8px);
        }
        100% {
          transform: translateY(0);
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
      color: ${colors.darkGray};

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
