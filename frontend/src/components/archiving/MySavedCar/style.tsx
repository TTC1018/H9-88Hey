import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Contianer = styled.div`
  height: 500px;
  gap: 15px;

  display: flex;
  align-items: center;
  justify-content: center;
`;

export const MyCarBox = styled.div`
  gap: 20px;

  display: grid;
  grid-template-columns: repeat(2, 1fr);
`;

export const EmptyBox = styled.div`
  width: 506px;
  height: 239px;
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

      ${fonts.headingBold4};
      color: #fff;
      text-align: center;

      background-color: ${colors.hyundaiPrimaryBlue};
      border-radius: 8px;
    `;
  }}
`;
