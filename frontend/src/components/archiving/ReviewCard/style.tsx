import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Contaienr = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 500px;
      height: 347px;
      padding: 30px 20px;

      border-radius: 8px;
      border: 1px solid ${colors.hyundaiSand};
      background: #fff;
    `;
  }}
`;

export const TitleWrapper = styled.div`
  width: 100%;
  margin-bottom: 10px;

  display: flex;
  justify-content: space-between;
`;

export const Title = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingBold3}
    `;
  }}
`;

export const SubTitle = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyRegular3}
    `;
  }}
`;

export const Time = styled.p`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      width: 148px;
      height: 24px;

      display: flex;
      justify-content: center;
      align-items: center;

      ${fonts.bodyMedium4}
      color: ${colors.hyundaiGold};

      border-radius: 16px;
      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const TextWrapper = styled.div`
  width: 100%;
  gap: 20px;

  display: flex;
`;

export const TextBox = styled.div`
  gap: 12px;

  display: flex;
`;

export const BodyText = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium3}
      white-space: nowrap;
    `;
  }}
`;

export const ColorText = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      color: #8b8b8b;
      ${fonts.bodyRegular3}
    `;
  }}
`;

export const OptionWrapper = styled.div`
  width: 100%;
  height: 60px;
  gap: 8px;
  margin: 10px 0;

  display: flex;
`;

export const OptionBox = styled.div`
  gap: 8px;

  display: flex;
  flex-wrap: wrap;
`;

export const Description = styled.p`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      padding: 15px 15px;
      margin: 15px 0;

      border-radius: 8px;
      background: ${colors.hyundaiLightSand};

      ${fonts.bodyRegular3}
    `;
  }}
`;

export const TagWrapper = styled.div`
  gap: 10px;

  display: flex;
  flex-wrap: wrap;
`;

export const Tag = styled.p`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      padding: 4px 8px;

      border-radius: 8px;
      background: ${colors.hyundaiLightSand};

      ${fonts.bodyRegular3}
    `;
  }}
`;

export const Enclosure = styled.div``;

export const SideBox = styled.div`
  gap: 10px;

  display: flex;
`;
