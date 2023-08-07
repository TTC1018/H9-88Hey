import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface SizeProps {
  isBig: boolean;
}

export const Container = styled.div`
  width: 343px;
  height: 186px;
  padding: 13px 0;
  gap: 24px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  border-radius: 8px;
  background: #fff;
`;

export const Fragment = styled.div``;

export const TextWrapper = styled.div`
  height: 66px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const Text = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyRegular3};
    `;
  }}
`;

export const Bold = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium3};
    `;
  }}
`;

export const ButtonWrapper = styled.div`
  display: flex;
  gap: 6px;
`;

export const CancleButton = styled.button<SizeProps>`
  ${({ theme, isBig }) => {
    const { colors, fonts } = theme;
    return css`
      height: 56px;
      width: ${isBig ? '126px' : '157px'};

      color: ${colors.darkGray};
      text-align: center;
      ${fonts.headingBold4};

      border-radius: 8px;
      background-color: ${colors.lightGray};
    `;
  }}
`;

export const ConfirmButton = styled.button<SizeProps>`
  ${({ theme, isBig }) => {
    const { colors, fonts } = theme;
    return css`
      height: 56px;
      width: ${isBig ? '187px' : '157px'};

      color: #fff;
      text-align: center;
      ${fonts.headingBold4};

      border-radius: 8px;
      background-color: ${colors.hyundaiPrimaryBlue};
    `;
  }}
`;
