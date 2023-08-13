import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const Flex = styled.div`
  display: flex;
  justify-content: center;
`;

export const SubTitle = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      width: 1024px;
      margin-top: 28px;

      ${fonts.headingMedium2};
    `;
  }}
`;

export const SummaryWrapper = styled.div`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 1024px;
      height: 230px;
      margin-top: 16px;
      padding: 40px 65px 0 65px;

      display: flex;
      flex-direction: column;

      border-radius: 8px;
      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const Name = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      ${fonts.headingMedium2};
    `;
  }}
`;

export const DetailWrapper = styled.div`
  width: 100%;
  height: 32px;

  display: flex;
  justify-content: space-between;
`;

export const Trim = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      margin-top: 6px;

      ${fonts.bodyRegular1};
    `;
  }}
`;

export const Price = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      ${fonts.headingMedium2};
    `;
  }}
`;

export const SummaryLine = styled.hr`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 910px;
      height: 3px;
      margin-top: 16.88px;

      background-color: ${colors.hyundaiSand};
      border: 0;
    `;
  }}
`;

export const ColorWrapper = styled.div`
  width: 100%;
  height: 32px;
  margin-top: 40px;

  display: flex;
  align-items: center;
`;

export const ColorType = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      ${fonts.headingMedium2};
    `;
  }}
`;

export const Ellipse = styled.img`
  width: 24px;
  height: 24px;

  margin-left: 24px;
`;

export const ColorName = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      margin-left: 16px;

      ${fonts.bodyRegular1};
    `;
  }}
`;

export const Space = styled.div`
  width: 49px;
  height: 32px;
`;
