import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const HeaderWrapper = styled.div`
  top: 0;

  position: sticky;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #fff;
  z-index: 2;
`;

export const ReviewWrapper = styled.div`
  width: 1024px;
  margin-top: 50px;

  display: flex;
  position: relative;
`;

export const InfoBox = styled.div`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      width: 100%;
      margin-top: 50px;

      display: flex;
      justify-content: center;

      ${fonts.bodyMedium1}
    `;
  }}
`;

export const DownButton = styled.button`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 50px;
      height: 50px;
      bottom: 30px;
      right: 30px;

      position: fixed;
      display: flex;
      justify-content: center;
      align-items: center;

      background-color: ${colors.hyundaiSand};
      border-radius: 8px;
    `;
  }}
`;
