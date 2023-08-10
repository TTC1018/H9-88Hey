import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 100vw;
  height: 100vh;
  top: 0;
  left: 0;

  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;

  background: rgba(35, 35, 35, 0.6);
  backdrop-filter: blur(2px);
`;

export const ModalContainer = styled.div`
  width: 476px;
  top: 50%;
  left: 50%;

  position: fixed;

  border-radius: 10px;
  background: #fff;
  box-shadow: -2px 0px 12px 0px rgba(78, 81, 94, 0.25);

  transform: translate(-50%, -50%);
`;

export const Header = styled.div`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      width: 100%;
      height: 80px;

      display: flex;
      justify-content: center;
      align-items: center;

      ${fonts.headingMedium4}
    `;
  }}
`;

export const TitleWrapper = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 476px;
      height: 42px;
      padding: 0 20px;

      display: flex;
      justify-content: space-between;
      align-items: center;

      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export const Title = styled.div`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium3}
    `;
  }}
`;

export const Price = styled.div`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingBold4}
    `;
  }}
`;

export const DescriptionWrapper = styled.div`
  width: 100%;
  padding: 24px 20px;
  gap: 8px;

  display: flex;
  flex-direction: column;
`;

export const DescriptionBox = styled.div`
  width: 100%;

  display: flex;
  justify-content: space-between;
`;

export const Description = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyRegular3}
    `;
  }}
`;
