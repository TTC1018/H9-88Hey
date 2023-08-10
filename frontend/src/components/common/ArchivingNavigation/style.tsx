import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.nav`
  width: 100%;
  padding: 23px 0;

  display: flex;
  justify-content: center;
`;

export const Wrapper = styled.div`
  max-width: 1024px;
  width: 100%;

  display: flex;
  align-items: center;
  justify-content: space-between;
  position: relative;
`;

export const TitleBox = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 133px;
      height: 40px;
      gap: 9px;

      display: flex;
      align-items: center;
      justify-content: center;
      position: absolute;
      left: 50%;
      transform: translateX(-50%);

      background-color: ${colors.hyundaiLightSand};
      border-radius: 18px;
    `;
  }}
`;

export const TitleText = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      ${fonts.headingMedium3}
      color: ${colors.black};
    `;
  }}
`;

export const ButtonBox = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      padding: 4px 0;
      gap: 8px;

      display: flex;
      align-items: center;
      justify-content: center;

      border-bottom: 1px solid ${colors.black};

      cursor: pointer;
    `;
  }}
`;

export const ButtonText = styled.span`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      ${fonts.headingMedium4}
      color: ${colors.black};
    `;
  }}
`;
