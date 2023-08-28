import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const Container = styled.div`
  width: 507px;

  display: flex;
  flex-direction: column;
`;

export const TitleWrapper = styled.div`
  display: flex;
  justify-content: space-between;
`;

export const TitleBox = styled.div`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      height: 36px;

      ${fonts.headingBold1};
      color: ${colors.black};
    `;
  }};
`;

export const PriceBox = styled.div`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      height: 32px;

      ${fonts.headingBold2};
      color: ${colors.black};
    `;
  }}
`;

export const Line = styled.hr`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 507px;
      height: 2px;
      margin-top: 8px;

      background-color: ${colors.darkGray};
    `;
  }}
`;

export const DescriptionWrapper = styled.div`
  height: 28px;
  gap: 7px;
  margin-top: 16px;

  display: flex;
  align-items: flex-end;
`;

export const SubTitleBox = styled.div`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      height: 28px;

      color: ${colors.black};
      ${fonts.headingMedium3};
    `;
  }}
`;

export const MessageBox = styled.div`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      width: 216px;
      height: 22px;

      color: ${colors.black};
      ${fonts.bodyRegular3};
    `;
  }}
`;

export const TagWrapper = styled.div`
  width: 507px;
  height: 30px;
  gap: 4px;
  margin-top: 12px;

  display: flex;
`;
