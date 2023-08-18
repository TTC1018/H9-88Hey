import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 330px;
      padding: 15px;

      border-radius: 8px;
      border: 1px solid ${colors.hyundaiSand};
    `;
  }}
`;

export const Image = styled.img`
  width: 300px;
  height: 124px;
`;

export const Text = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      margin-top: 5px;
      ${fonts.headingMedium3};
    `;
  }}
`;

export const SubOptions = styled.p`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      width: 300px;

      ${fonts.bodyMedium2}
      color: ${colors.hyundaiPrimaryBlue};
    `;
  }}
`;

export const Review = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      width: 300px;
      padding: 10px 0;

      ${fonts.bodyRegular3}
    `;
  }}
`;

export const TagWrapper = styled.div`
  width: 300px;
  gap: 8px;

  display: flex;
  flex-wrap: wrap;
`;

export const Tag = styled.p`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      padding: 4px 8px;

      ${fonts.bodyRegular3}

      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const Line = styled.hr`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      margin: 5px 0;

      background-color: ${colors.lightGray};
    `;
  }}
`;
