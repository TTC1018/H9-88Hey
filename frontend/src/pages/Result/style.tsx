import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const Container = styled.div`
  width: 100%;

  display: flex;
  flex-direction: column;
  aign-items: center;
`;

export const TitleWrapper = styled.div`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 100%;
      height: 277px;
      margin-top: 32px;
      padding-top: 76px;

      display: flex;
      justify-content: center;

      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const TitleBox = styled.div`
  width: 1024px;

  display: flex;
  flex-direction: column;
`;

export const Title = styled.p`
  width: 328px;

  color: #000;
  font-family: HyundaiSansMedium;
  font-size: 64px;
  font-style: normal;
  font-weight: 500;
  line-height: 36px;
  letter-spacing: -1.28px;
`;

export const TitleLine = styled.hr`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 605px;
      height: 1px;
      margin: 13px 0 0 0;

      background-color: ${colors.mediumGray};
      border: 0;
    `;
  }}
`;

export const Message = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      width: 328px;
      margin-top: 17px;

      color: #232323;
      ${fonts.headingMedium1}
    `;
  }}
`;

export const Image = styled.img`
  width: 851px;
  height: 465px;
  top: 25px;
  right: 200px;
  z-index: 1;

  position: absolute;
`;

export const DescriptionBox = styled.div`
  margin-top: 3px;

  display: flex;
`;

export const RightArrow = styled.img`
  width: 16px;
  height: 16px;
`;

export const Description = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      color: #232323;
      ${fonts.captionRegular}
    `;
  }}
`;

export const LineWrapper = styled.div`
  display: flex;
  justify-content: center;
`;

export const TitleBoxLine = styled.hr`
  width: 1024px;
  height: 4px;
  margin-top: 83px;

  background-color: #f3f2f5;
  border: 0;
`;

export const SubTitleWrapper = styled.div`
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
  display: flex;
  justify-content: center;
`;

export const SummaryBox = styled.div`
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

export const DetailBox = styled.div`
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

export const ColorBox = styled.div`
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

export const FooterLine = styled.hr`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 100%;
      height: 18px;
      margin: 42px 0 0 0;

      border: 0;
      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const MenuWrapper = styled.div`
  width: 100%;

  display: flex;
  justify-content: center;
`;

export const MenuBox = styled.div`
  width: 1024px;

  display: flex;
  justify-content: space-between;
`;

export const ConsultMenuBox = styled.div`
  width: 355px;
  height: 246px;
  margin-top: 36px;
  gap: 28px;

  display: flex;
  flex-direction: column;
`;

export const ConsultText = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      ${fonts.headingMedium3};
    `;
  }}
`;

export const ConsultButton = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      width: 355px;
      height: 52px;
      padding: 15px 24px;

      display: flex;
      justify-content: space-between;
      position: relative;

      ${fonts.bodyMedium3};

      border-radius: 10px;
      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const FooterRightArrow = styled.img`
  top: 20px;
  right: 18px;

  position: absolute;
`;

export const PurchaseMenuBox = styled.div`
  width: 510px;
  height: 246px;
  margin-top: 36px;
  gap: 28px;

  display: flex;
  flex-direction: column;
`;

export const PurchaseButtonBox = styled.div`
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-gap: 8px 10px;
`;

export const PurchaseButton = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      width: 249px;
      height: 52px;
      padding: 15px 27px;

      ${fonts.bodyMedium3};

      border-radius: 8px;
      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const FooterContainer = styled.div`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 100%;

      display: flex;
      justify-content: center;

      border-radius: 16px 16px 0px 0px;
      background-color: ${colors.hyundaiSand};
    `;
  }}
`;

export const FooterWrapper = styled.div`
  width: 1024px;
  height: 108px;

  display: flex;
  align-items: center;
`;

export const FooterBox = styled.div`
  width: 1024px;
  height: 50px;

  display: flex;
  justify-content: flex-end;
  align-items: center;
`;

export const FooterPriceBox = styled.div`
  height: 29px;
  gap: 5px;

  display: flex;
  align-items: center;
`;

export const FooterPriceText = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      height: 20px;
      margin-right: 6px;
      padding-top: 3px;

      color: ${colors.darkGray};
      ${fonts.bodyMedium3};
    `;
  }}
`;

export const FooterPrice = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      height: 29px;
      margin-right: 5px;

      ${fonts.headingBold1};
    `;
  }}
`;

export const FooterPriceUnitBox = styled.div`
  height: 40px;

  display: flex;
  align-items: flex-end;
`;

export const FooterPriceUnit = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      height: 32px;
      margin-right: 24px;

      display: flex;
      align-items: flex-end;

      ${fonts.bodyMedium2};
    `;
  }}
`;

export const FooterButton = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      width: 176px;
      height: 50px;

      color: ${colors.hyundaiNeutral};
      ${fonts.headingBold4};

      border-radius: 8px;
      background-color: ${colors.hyundaiPrimaryBlue};
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
