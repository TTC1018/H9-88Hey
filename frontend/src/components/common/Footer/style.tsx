import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.footer`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 100%;
      height: 108px;
      gap: 18px;

      display: flex;
      align-items: center;
      justify-content: center;

      background-color: ${colors.hyundaiSand};
      border-radius: 16px 16px 0 0;
      border-top: 1.196px solid ${colors.lightGray};
      box-shadow: -2px 0 12px 0 rgba(217, 216, 215, 0.2);
    `;
  }}
`;

export const TrimWrapper = styled.div`
  width: 150px;
  height: 75px;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

export const CarName = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.headingBold4}
      color: ${colors.black};
    `;
  }}
`;

export const TrimDetail = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.bodyRegular3}
      color: ${colors.black};
    `;
  }}
`;

export const ColorWrapper = styled.div`
  width: 170px;
  height: 75px;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

export const ColorBox = styled.div`
  gap: 10px;

  display: flex;
  align-items: center;
`;

export const ColorTitle = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.bodyMedium3};
      color: ${colors.black};

      white-space: nowrap;
    `;
  }}
`;

export const ColorName = styled.div`
  gap: 7px;

  display: flex;
  align-items: center;

  overflow: hidden;
`;

interface ColorCircleProps {
  imageUrl: string;
}

export const ColorCircle = styled.div<ColorCircleProps>`
  ${({ imageUrl }) => {
    return css`
      width: 16px;
      height: 16px;

      flex-shrink: 0;

      background-image: url(${imageUrl});
      background-position: center;
      border-radius: 50%;
    `;
  }}
`;

export const ColorNameText = styled.div`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.bodyRegular3};
      color: ${colors.black};

      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    `;
  }}
`;

export const OptionWrapper = styled.div`
  width: 380px;
  height: 75px;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

export const OptionBox = styled.div`
  gap: 8px;

  display: flex;
  align-items: center;

  cursor: pointer;
`;

export const Option = styled.div`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      max-width: 100px;
      padding: 4px 8px;

      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;

      ${fonts.bodyRegular3}
      color: ${colors.hyundaiLightSand};

      background: ${colors.black};
      border-radius: 6px;
    `;
  }}
`;

export const PriceWrapper = styled.div`
  width: 180px;
  height: 75px;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

export const PriceText = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.headingBold1}
      color: ${colors.black};
    `;
  }}
`;

export const PriceUnitText = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      margin-left: 4px;

      ${fonts.headingBold1}
      color: ${colors.black};
      font-size: 18px;
    `;
  }}
`;

export const ButtonWrapper = styled.div`
  gap: 8px;

  display: flex;
  align-items: center;
`;

export const Title = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.bodyMedium4}
      color: ${colors.darkGray};
    `;
  }}
`;

export const PrevButton = styled.button`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      width: 105px;
      height: 50px;

      flex-shrink: 0;

      ${fonts.headingBold4}

      background-color: ${colors.hyundaiNeutral};
      border-radius: 8px;
    `;
  }}
`;

export const NextButton = styled.button`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      width: 150px;
      height: 50px;

      flex-shrink: 0;

      ${fonts.headingBold4}
      color: ${colors.hyundaiNeutral};

      background-color: ${colors.hyundaiPrimaryBlue};
      border-radius: 8px;
    `;
  }}
`;

export const Division = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 1px;
      height: 70px;

      background-color: ${colors.mediumGray};
    `;
  }}
`;

export const TitleButton = styled.button`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.bodyMedium2}
      color: ${colors.darkGray};
    `;
  }}
`;

export const TitleBox = styled.div`
  width: 100%;

  display: flex;
  justify-content: space-between;
`;
