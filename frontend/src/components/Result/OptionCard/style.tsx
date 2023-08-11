import styled from '@emotion/styled';
import { css } from '@emotion/react';

interface Props {
  hasMargin: boolean;
}

export const Container = styled.div<Props>`
  ${({ hasMargin }) => {
    return css`
      width: 489px;
      height: 99px;
      gap: 17px;
      margin-right: ${hasMargin ? '62px' : '0'};

      display: flex;
    `;
  }}
`;

export const Bar = styled.div`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 10px;
      height: 99px;

      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const CardWrapper = styled.div`
  ${({ theme }) => {
    const { colors } = theme;

    return css`
      width: 462px;
      height: 99px;
      gap: 12px;

      display: flex;
      align-items: center;

      border-top: 1px solid ${colors.hyundaiSand};
      border-bottom: 1px solid ${colors.hyundaiSand};
    `;
  }}
`;

export const ImageWrapper = styled.div`
  width: 69px;
  height: 69px;

  overflow: hidden;
  border-radius: 10%;
`;

export const Image = styled.img`
  width: 69px;
  height: 69px;
`;

export const TextWrapper = styled.div`
  width: 382px;
  height: 69px;

  display: flex;
  flex-direction: column;
`;

export const TitleWrapper = styled.div`
  gap: 10px;

  display: flex;
`;

export const Title = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      ${fonts.bodyMedium3};
    `;
  }}
`;

export const TitleBarWrapper = styled.div`
  display: flex;
  align-items: center;
`;

export const TitleBar = styled.img`
  width: 1px;
  height: 10.5px;
`;

export const Description = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      ${fonts.bodyRegular3};
    `;
  }}
`;
