import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const Container = styled.div`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 507px;
      height: 152px;
      padding: 24px 32px;

      display: flex;
      flex-direction: column;

      border-radius: 8px;
      border: 2px solid ${colors.hyundaiPrimaryBlue};
      background: rgba(0, 44, 95, 0.1);
    `;
  }}
`;

export const TitleWrapper = styled.div`
  width: 443px;
  height: 152px;

  display: flex;
  justify-content: space-between;
`;

export const TitleBox = styled.div`
  gap: 8px;

  display: flex;
`;

export const Ellipse = styled.div`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 22px;
      height: 22px;
      padding-right: 0.5px;

      font-family: ‘HyundaiSansBold’;
      color: ${colors.hyundaiLightSand};
      text-align: center;
      font-size: 12px;
      font-style: normal;
      font-weight: 700;
      line-height: 20px;
      letter-spacing: -0.6px;

      border-radius: 50%;
      border: 2px solid #fff;
      background-color: ${colors.hyundaiPrimaryBlue};
    `;
  }}
`;

export const Title = styled.div`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      height: 24px;

      color: ${colors.hyundaiPrimaryBlue};
      ${fonts.headingMedium4}
    `;
  }}
`;

export const OrderBox = styled.div`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 33px;
      height: 17px;

      display: flex;
      justify-content: center;
      align-items: center;

      border-radius: 13px;
      background-color: ${colors.darkGray};
    `;
  }}
`;

export const Order = styled.p`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      height: 100%;

      color: ${colors.hyundaiLightSand};
      ${fonts.captionRegular}
    `;
  }}
`;

export const Line = styled.hr`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 443px;
      height: 1px;

      background-color: ${colors.hyundaiPrimaryBlue};
    `;
  }}
`;

export const DescriptionWrapper = styled.div`
  width: 100%;

  display: flex;
`;

export const Description = styled.div`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      width: 380px;
      height: 61px;

      color: ${colors.hyundaiPrimaryBlue};
      ${fonts.bodyRegular3}
      overflow-y: scroll;
      ::-webkit-scrollbar {
        display: none;
      }
    `;
  }}
`;
