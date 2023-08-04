import { css } from '@emotion/react';
import styled from '@emotion/styled';

const Container = styled.footer`
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
      border-radius: 16px 16px 0px 0px;
      border-top: 1.196px solid ${colors.lightGray};
      box-shadow: -2px 0px 12px 0px rgba(217, 216, 215, 0.2);
    `;
  }}
`;

const TrimWrapper = styled.div`
  width: 130px;
  height: 75px;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const CarName = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.headingBold4}
      color: ${colors.black};
    `;
  }}
`;

const TrimDetail = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.bodyRegular3}
      color: ${colors.black};
    `;
  }}
`;

const ColorWrapper = styled.div`
  width: 150px;
  height: 75px;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const ColorBox = styled.div`
  gap: 12px;

  display: flex;
  align-items: center;
`;

const ColorTitle = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.bodyMedium3};
      color: ${colors.black};
    `;
  }}
`;

const ColorName = styled.div`
  gap: 8px;

  display: flex;
  align-items: center;
`;

const ColorCircle = styled.div`
  width: 16px;
  height: 16px;

  flex-shrink: 0;

  background-color: #191f32;
  border-radius: 50%;
`;

const ColorNameText = styled.div`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.bodyRegular3};
      color: ${colors.black};
    `;
  }}
`;

const OptionWrapper = styled.div`
  width: 380px;
  height: 75px;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const OptionBox = styled.div`
  gap: 8px;

  display: flex;
  align-items: center;
`;

const Option = styled.div`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
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

const PriceWrapper = styled.div`
  width: 200px;
  height: 75px;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const PriceText = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.headingBold1}
      color: ${colors.black};
    `;
  }}
`;

const PriceUnitText = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.headingBold1}
      color: ${colors.black};
      font-size: 18px;
    `;
  }}
`;

const ButtonWrapper = styled.div`
  gap: 8px;

  display: flex;
  align-items: center;
`;

const Title = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.bodyMedium4}
      color: ${colors.darkGray};
    `;
  }}
`;

const PrevButton = styled.button`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      width: 121px;
      height: 50px;

      flex-shrink: 0;

      ${fonts.headingBold4}

      background-color: ${colors.hyundaiNeutral};
      border-radius: 8px;
    `;
  }}
`;

const NextButton = styled.button`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      width: 176px;
      height: 50px;

      flex-shrink: 0;

      ${fonts.headingBold4}
      color: ${colors.hyundaiNeutral};

      background-color: ${colors.hyundaiPrimaryBlue};
      border-radius: 8px;
    `;
  }}
`;

const Division = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 1px;
      height: 70px;

      background-color: ${colors.mediumGray};
    `;
  }}
`;

export {
  Container,
  TrimWrapper,
  CarName,
  TrimDetail,
  ColorWrapper,
  ColorBox,
  ColorTitle,
  ColorName,
  ColorCircle,
  ColorNameText,
  OptionWrapper,
  OptionBox,
  Option,
  PriceWrapper,
  PriceText,
  PriceUnitText,
  ButtonWrapper,
  Title,
  PrevButton,
  NextButton,
  Division,
};
