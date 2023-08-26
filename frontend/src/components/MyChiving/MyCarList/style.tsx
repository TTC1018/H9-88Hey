import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface OptionCardProps {
  imageUrl: string;
}

interface subTitleTextProps {
  isSaved: boolean;
}

export const Container = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 506px;
      height: 260px;
      padding: 17px 30px;

      background-color: #fff;
      border: 1px solid ${colors.hyundaiSand};
      border-radius: 8px;

      cursor: pointer;
      opacity: 0;
      animation: fadeIn 0.5s ease-in-out forwards;
      @keyframes fadeIn {
        from {
          opacity: 0;
        }
        to {
          opacity: 1;
        }
      }
    `;
  }}
`;

export const Wrapper = styled.div``;

export const InfoBox = styled.div`
  height: 20px;
`;

export const InfoText = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      ${fonts.bodyMedium4}
      color: ${colors.darkGray};

      &::before {
        color: ${colors.alertPrimary};
        content: '* ';
      }
    `;
  }}
`;

export const MainBox = styled.div`
  height: 76px;
  margin-bottom: 12px;

  display: flex;
  align-items: flex-start;
  justify-content: space-between;
`;

export const InfoClosure = styled.div`
  gap: 5px;

  display: flex;
  flex-direction: column;
  align-items: flex-start;
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

export const ColorBox = styled.div`
  gap: 10px;

  display: flex;
  align-items: center;
`;

export const ColorName = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      ${fonts.bodyRegular4};
      color: ${colors.black};
    `;
  }}
`;

export const SubTitle = styled.div`
  gap: 10px;

  display: flex;
  align-items: center;
`;

export const SubTitleText = styled.span<subTitleTextProps>`
  ${({ theme, isSaved }) => {
    const { colors, fonts } = theme;
    return css`
      padding: 3px 12px;

      ${fonts.bodyMedium4};
      color: ${isSaved ? colors.hyundaiGold : colors.alertPrimary};

      background-color: ${isSaved ? colors.hyundaiLightSand : '#ffd1cd'};
      border-radius: 99px;
    `;
  }}
`;

export const XButton = styled.button``;

export const OptionBox = styled.div`
  height: 120px;
  gap: 4px;

  display: flex;
  align-items: center;

  overflow-x: scroll;
  &::-webkit-scrollbar {
    display: none;
  }
`;

export const OptionBoxText = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      ${fonts.bodyRegular3};
      color: ${colors.black};

      &::before {
        content: '*';
      }
    `;
  }}
`;

export const OptionCard = styled.div<OptionCardProps>`
  ${({ theme, imageUrl }) => {
    const { colors } = theme;
    return css`
      width: 120px;
      height: 120px;

      flex: none;
      position: relative;

      background: ${imageUrl ? `url(${imageUrl})` : colors.hyundaiLightSand};
      background-repeat: no-repeat;
      background-size: cover;
      background-color: transparent;
      border-radius: 4px;
    `;
  }}
`;

export const EmptyOptionCard = styled.div`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      width: 120px;
      height: 120px;

      flex: none;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;

      ${fonts.bodyMedium4}

      background: ${colors.hyundaiLightSand};
      border-radius: 4px;
    `;
  }}
`;

export const Text = styled.p`
  text-align: center;
`;

export const OptionCardText = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      bottom: 6px;
      left: 6px;
      padding: 2px 4px;

      position: absolute;

      ${fonts.bodyRegular4};
      color: ${colors.hyundaiNeutral};

      background-color: ${colors.black};
      border-radius: 4px;
    `;
  }}
`;
