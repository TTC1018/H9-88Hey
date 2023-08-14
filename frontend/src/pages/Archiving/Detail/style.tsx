import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 100vw;
`;

export const HeaderWrapper = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 100%;
      height: 334px;
      padding-top: 30px;

      display: flex;
      justify-content: center;

      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const DescriptionWrapper = styled.div`
  width: 100%;
  padding: 17px 0 30px 0;

  display: flex;
  justify-content: center;
`;

export const Line = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 100%;
      height: 18px;

      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const OptionWrapper = styled.div``;
