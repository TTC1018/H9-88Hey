import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 1024px;
  gap: 30px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const Wrapper = styled.div`
  width: 1024px;
  gap: 30px;

  display: flex;
  justify-content: center;
`;

export const OptionBox = styled.div`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      gap: 10px;

      display: flex;
      flex-direction: column;

      ${fonts.headingMedium4}
    `;
  }}
`;
export const Title = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 973px;
      height: 72px;

      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;

      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const Regular = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyRegular2}
    `;
  }}
`;

export const Focus = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingBold3}
    `;
  }}
`;
