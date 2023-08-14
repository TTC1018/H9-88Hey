import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;

export const NoDataInfoBox = styled.div`
  gap: 20px;

  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const NoDataInfoText = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      ${fonts.bodyMedium3};
      color: ${colors.black};
    `;
  }}
`;

export const CreateMyCarButton = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      width: 343px;
      height: 56px;

      ${fonts.bodyMedium2};
      color: #fff;
      text-align: center;

      background-color: ${colors.hyundaiPrimaryBlue};
      border-radius: 8px;
    `;
  }}
`;
