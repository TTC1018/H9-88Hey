import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.main`
  width: 100vw;

  display: flex;
  align-items: center;
  justify-content: center;
`;

export const Wrapper = styled.div`
  width: 1024px;
`;

export const TitleText = styled.h3`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      ${fonts.headingMedium3};
      color: ${colors.black};
    `;
  }}
`;

export const Division = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      margin: 8px 0 25px 0;

      width: 100%;
      height: 6px;

      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const MyCarMain = styled.div`
  gap: 15px;

  display: flex;
  align-items: center;
  justify-content: center;
`;

export const MyCarBox = styled.div`
  gap: 20px;

  display: grid;
  grid-template-columns: repeat(2, 1fr);
`;

export const EmptyBox = styled.div`
  width: 506px;
  height: 239px;
`;
