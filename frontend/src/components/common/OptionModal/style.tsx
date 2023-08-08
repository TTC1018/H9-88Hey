import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const Container = styled.div`
  width: 100%;
  height: 100%;
  top: 0;
  left: 0;

  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;

  background: rgba(35, 35, 35, 0.6);
  backdrop-filter: blur(2px);
`;

export const ModalWrapper = styled.div`
  width: 800px;
  height: 510px;

  display: flex;
  flex-direction: column;

  border-radius: 8px;
  background-color: #fff;
`;

export const TitleBox = styled.div`
  width: 800px;
  height: 80px;

  display: flex;
  position: relative;
  justify-content: center;
  align-items: center;
`;

export const Title = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingMedium4};
    `;
  }}
`;

export const Icon = styled.img`
  top: 20px;
  right: 28px;

  position: absolute;

  cursor: pointer;
`;

export const Line = styled.hr`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 800px;
      height: 1px;
      margin: 0;

      border: 0;
      background-color: ${colors.lightGray};
    `;
  }}
`;

export const ContentBox = styled.div`
  width: 800px;
  height: 356px;
  gap: 24px;

  display: flex;
  flex-direction: column;
`;

export const ImageBox = styled.div`
  width: 800px;
  height: 263px;
  margin-top: 16px;

  display: flex;
  justify-content: center;
  overflow: hidden;

  border-radius: 8px;
`;

export const Image = styled.img`
  width: 668px;
  height: 263px;
`;

export const DescriptionBox = styled.div`
  width: 800px;

  display: flex;
  justify-content: center;
`;

export const Description = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      width: 668px;

      color: #000;
      ${fonts.bodyRegular4};
    `;
  }}
`;

export const ButtonBox = styled.div`
  width: 800px;
  height: 74px;

  display: flex;
  justify-content: center;
`;

export const Button = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      width: 176px;
      height: 50px;

      color: ${colors.hyundaiLightSand};
      ${fonts.headingBold4};

      border-radius: 8px;
      background-color: ${colors.hyundaiPrimaryBlue};
    `;
  }}
`;
