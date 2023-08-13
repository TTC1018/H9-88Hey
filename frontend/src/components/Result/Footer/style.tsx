import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const Container = styled.div`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 100%;

      display: flex;
      justify-content: center;

      border-radius: 16px 16px 0px 0px;
      background-color: ${colors.hyundaiSand};
    `;
  }}
`;

export const Wrapper = styled.div`
  width: 1024px;
  height: 108px;

  display: flex;
  align-items: center;
`;

export const FooterBox = styled.div`
  width: 1024px;
  height: 50px;

  display: flex;
  justify-content: flex-end;
  align-items: center;
`;

export const PriceBox = styled.div`
  height: 29px;
  gap: 5px;

  display: flex;
  align-items: center;
`;

export const PriceText = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      height: 20px;
      margin-right: 6px;
      padding-top: 3px;

      color: ${colors.darkGray};
      ${fonts.bodyMedium3};
    `;
  }}
`;

export const Price = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      height: 29px;
      margin-right: 5px;

      ${fonts.headingBold1};
    `;
  }}
`;

export const PriceUnitBox = styled.div`
  height: 40px;

  display: flex;
  align-items: flex-end;
`;

export const PriceUnit = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      height: 32px;
      margin-right: 24px;

      display: flex;
      align-items: flex-end;

      ${fonts.bodyMedium2};
    `;
  }}
`;

export const Button = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      width: 176px;
      height: 50px;

      color: ${colors.hyundaiNeutral};
      ${fonts.headingBold4};

      border-radius: 8px;
      background-color: ${colors.hyundaiPrimaryBlue};
    `;
  }}
`;
