import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 1024px;
  height: 334px;

  display: flex;
  justify-content: space-between;
`;

export const TextWrapper = styled.div`
  width: 381px;
`;

export const TextBox = styled.div`
  width: 100%;

  display: flex;
  justify-content: space-between;
`;

export const Title = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingBold1}
    `;
  }}
`;

export const SubTitle = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyRegular1}
    `;
  }}
`;

export const Date = styled.p`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      width: 148px;
      height: 24px;
      margin-top: 6px;

      display: flex;
      justify-content: center;
      align-items: center;

      ${fonts.bodyMedium4}
      color: ${colors.hyundaiGold};

      border-radius: 16px;
      background-color: ${colors.hyundaiSand};
    `;
  }}
`;

export const ColorBox = styled.div`
  height: 50px;
  gap: 12px;

  display: flex;
  align-items: center;
`;

export const BodyText = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium3}
      white-space: nowrap;
    `;
  }}
`;

export const ColorText = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      color: #8b8b8b;
      ${fonts.bodyRegular3}
    `;
  }}
`;

export const Line = styled.hr`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 160%;
      height: 1px;

      background-color: ${colors.mediumGray};
    `;
  }}
`;

export const Review = styled.div`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      margin-top: 15px;
      padding: 12px 17px;

      ${fonts.bodyRegular3}

      background-color: ${colors.hyundaiNeutral};
      border-radius: 8px;
      border: 1px solid ${colors.lightGray};
    `;
  }}
`;

export const Image = styled.img`
  width: 550px;
  height: 340px;

  transform: scale(1.6);
`;

export const Enclosure = styled.div``;
