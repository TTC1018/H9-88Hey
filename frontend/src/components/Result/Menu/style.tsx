import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const Container = styled.div`
  display: flex;
  justify-content: center;
`;

export const MenuWrapper = styled.div`
  width: 1024px;

  display: flex;
  justify-content: space-between;
`;

export const ConsultMenuWrapper = styled.div`
  width: 355px;
  height: 246px;
  margin-top: 36px;
  gap: 28px;

  display: flex;
  flex-direction: column;
`;

export const ConsultText = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      ${fonts.headingMedium3};
    `;
  }}
`;

export const ConsultButton = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      width: 355px;
      height: 52px;
      padding: 15px 24px;

      display: flex;
      justify-content: space-between;
      position: relative;

      ${fonts.bodyMedium3};

      border-radius: 10px;
      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const FooterRightArrow = styled.img`
  top: 20px;
  right: 18px;

  position: absolute;
`;

export const PurchaseMenuWrapper = styled.div`
  width: 510px;
  height: 246px;
  margin-top: 36px;
  gap: 28px;

  display: flex;
  flex-direction: column;
`;

export const PurchaseButtonBox = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 8px 10px;
`;

export const PurchaseButton = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      width: 249px;
      height: 52px;
      padding: 15px 27px;

      ${fonts.bodyMedium3};

      border-radius: 8px;
      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;
