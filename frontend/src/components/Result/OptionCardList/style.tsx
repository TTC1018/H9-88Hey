import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const OptionTitleWrapper = styled.div`
  display: flex;
  justify-content: center;
`;

export const OptionTitleBox = styled.div`
  width: 1024px;
  height: 32px;
  margin-top: 42px;

  display: flex;
  align-items: center;
`;

export const OptionTitle = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      ${fonts.headingMedium1};
    `;
  }}
`;

export const OptionCount = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      margin-left: 21px;

      color: ${colors.hyundaiGold};
      ${fonts.headingMedium2};
    `;
  }}
`;

export const OptionUnit = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      ${fonts.headingMedium2};
    `;
  }}
`;

export const OptionCardWrapper = styled.div`
  width: 100%;
  margin-top: 18px;

  display: flex;
  justify-content: center;
`;

export const OptionCardBox = styled.div`
  width: 1040px;
  gap: 10px;

  display: flex;
  flex-wrap: wrap;
`;
