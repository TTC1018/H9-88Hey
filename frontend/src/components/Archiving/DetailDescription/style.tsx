import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 1024px;
`;

export const RegularText = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyRegular1}
    `;
  }}
`;

export const MediumText = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium3}
    `;
  }}
`;

export const Price = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingBold1}
    `;
  }}
`;

export const Wrapper = styled.div`
  max-height: 99px;
  margin-top: 54px;

  display: flex;
  justify-content: space-between;
`;

export const OptionBox = styled.div`
  width: 530px;
  margin-top: 10px;
  gap: 8px;

  display: flex;
  flex-wrap: wrap;
`;

export const Option = styled.div`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      padding: 4px 12px;

      ${fonts.bodyRegular3}

      border: 0.5px solid ${colors.lightGray};
    `;
  }}
`;

export const Button = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      width: 343px;
      height: 56px;

      ${fonts.headingBold4};
      color: ${colors.hyundaiLightSand};

      border-radius: 8px;
      background: ${colors.hyundaiPrimaryBlue};
    `;
  }}
`;

export const ButtonBox = styled.div`
  height: 56px;
  gap: 14px;

  display: flex;
  align-items: center;
`;

export const Encloser = styled.div``;
