import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 506px;
      height: 239px;
      padding: 17px 30px;

      background-color: #fff;
      border: 1px solid ${colors.hyundaiSand};
      border-radius: 8px;
    `;
  }}
`;

export const Wrapper = styled.div``;

export const InfoBox = styled.div`
  height: 20px;
`;

export const AlertStar = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      ${fonts.bodyMedium4}
      color: ${colors.alertPrimary};
    `;
  }}
`;

export const InfoText = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      ${fonts.bodyMedium4}
      color: ${colors.darkGray};
    `;
  }}
`;

export const MainBox = styled.div`
  margin-bottom: 12px;

  display: flex;
  align-items: flex-start;
  justify-content: space-between;
`;

export const Title = styled.div`
  gap: 5px;

  display: flex;
  flex-direction: column;
`;

export const TitleText = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      ${fonts.headingBold4};
      color: ${colors.black};
    `;
  }}
`;

export const TrimText = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      ${fonts.bodyRegular3};
      color: ${colors.black};
    `;
  }}
`;

export const SubTitle = styled.div`
  gap: 10px;

  display: flex;
  align-items: center;
`;

export const subTitleText = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      padding: 4px 12px;

      ${fonts.bodyMedium4};
      color: ${colors.alertPrimary};

      background-color: #ffd1cd;
      border-radius: 99px;
    `;
  }}
`;

export const XButton = styled.button``;

export const OptionBox = styled.div`
  gap: 4px;

  display: flex;
  align-content: center;
`;

interface OptionCardProps {
  isLastCard: boolean;
}

export const OptionCard = styled.div<OptionCardProps>`
  ${({ theme, isLastCard }) => {
    const { colors } = theme;
    return css`
      width: ${!isLastCard ? '120px' : '60px'};
      height: 120px;

      background-color: ${colors.hyundaiLightSand};
      border-radius: 4px;
    `;
  }}
`;
