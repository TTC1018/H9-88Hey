import styled from '@emotion/styled';
import { css } from '@emotion/react';

interface Props {
  isEmpty: boolean;
}

export const Container = styled.div`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 507px;
      height: 153px;

      display: flex;
      flex-direction: column;

      border-radius: 8px;
      border: 2px solid ${colors.hyundaiPrimaryBlue};
      background: rgba(0, 44, 95, 0.1);
    `;
  }}
`;

export const TitleWrapper = styled.div`
  width: 507px;
  height: 63px;
  padding: 15px 32px;

  display: flex;
  justify-content: space-between;
`;

export const TitleBox = styled.div`
  height: 100%;
  gap: 8px;

  display: flex;
  align-items: center;
`;

export const Ellipse = styled.div`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 22px;
      height: 22px;
      padding-right: 0.5px;

      font-family: HyundaiSansBold;
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
      height: 17px;

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
      margin: 0 32px;

      border: 0;
      background-color: ${colors.hyundaiPrimaryBlue};
    `;
  }}
`;

export const DescriptionWrapper = styled.div`
  width: 100%;
  height: 89px;
  gap: 13px;
  margin: 8px 0;

  display: flex;
`;

export const Description = styled.div<Props>`
  ${({ theme, isEmpty }) => {
    const { colors, fonts } = theme;

    return css`
      width: 380px;
      height: 73px;

      color: ${isEmpty ? 'rgba(0, 44, 95, 0.40)' : colors.hyundaiPrimaryBlue};
      ${fonts.bodyRegular3}
      overflow-y: scroll;
    `;
  }}
`;

export const Flex = styled.div`
  display: flex;
  align-items: center;
`;
