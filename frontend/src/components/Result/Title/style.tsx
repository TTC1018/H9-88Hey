import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const Container = styled.div`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 100%;
      height: 334px;
      margin-top: 30px;

      display: flex;
      justify-content: center;

      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const Box = styled.div`
  display: flex;
  width: 1024px;
  align-items: center;
  justify-content: space-between;
`;

export const TitleWrapper = styled.div`
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
      width: 160%;
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
  width: 550px;
  height: 340px;

  transform: scale(1.6);
`;

export const DescriptionWrapper = styled.div`
  margin-top: 3px;

  display: flex;
  align-items: center;
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
