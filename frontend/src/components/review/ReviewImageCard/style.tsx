import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  height: 447px;
  width: 419px;
  padding: 0 25px;

  position: relative;
`;

export const Image = styled.img`
  width: 369px;
  height: 213px;

  object-fit: cover;
  transform: scale(1.4);
`;

export const Svg = styled.svg`
  bottom: 0;
  left: 0;

  position: absolute;

  z-index: -1;
`;

export const Wrapper = styled.div`
  padding: 0 19px;
`;

export const Title = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      margin: 14px 0;

      ${fonts.headingBold1};
    `;
  }}
`;

export const Regular = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyRegular3};
    `;
  }}
`;

export const PriceBox = styled.div`
  width: 100%;

  display: flex;
  justify-content: flex-end;
`;

export const Medium = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium3};
    `;
  }}
`;

export const Line = styled.hr`
  width: 100%;
  height: 1px;
  margin: 8px 0;
`;

export const ColorBox = styled.div`
  height: 30px;
  gap: 12px;

  display: flex;
  align-items: center;
`;
