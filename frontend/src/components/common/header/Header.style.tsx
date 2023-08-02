import { css } from '@emotion/react';
import styled from '@emotion/styled';

const Container = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 100%;
      padding: 15px 70px;

      display: flex;
      justify-content: space-between;

      background-color: ${colors.hyundaiSand};
    `;
  }}
`;

const Wrapper = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
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

const AutoSavingBox = styled.div`
  display: flex;
  align-items: center;
  gap: 5px;
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
