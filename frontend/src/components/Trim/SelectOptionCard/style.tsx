import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface ActiveStateProps {
  isActive: boolean;
}
interface TextSizeProps {
  isBig: boolean;
}

const Container = styled.div<ActiveStateProps>`
  ${({ theme, isActive }) => {
    const { colors } = theme;
    return css`
      width: 242px;
      height: 251px;
      padding: 20px;

      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: space-between;
      position: relative;

      color: ${isActive ? colors.hyundaiPrimaryBlue : colors.black};

      border-radius: 8px;
      border: 3px solid ${isActive ? colors.hyundaiPrimaryBlue : colors.hyundaiLightSand};
      background: ${isActive ? 'rgba(0, 44, 95, 0.10)' : colors.hyundaiLightSand};

      cursor: pointer;

      &::before,
      &::after {
        width: 0;
        height: 0;

        position: absolute;
        visibility: hidden;

        border-radius: 8px;

        content: '';
        transition: 0.2s ease-in-out;
        transform: translateZ(0);
      }
      &::before {
        top: -2px;
        left: -2px;

        border-top: 2px solid ${colors.hyundaiPrimaryBlue};
        border-left: 2px solid ${colors.hyundaiPrimaryBlue};
      }

      &::after {
        right: -2px;
        bottom: -2px;

        border-bottom: 2px solid ${colors.hyundaiPrimaryBlue};
        border-right: 2px solid ${colors.hyundaiPrimaryBlue};
      }

      &:hover::before,
      &:hover::after {
        width: calc(2px + 100%);
        height: calc(2px + 100%);

        ${!isActive && 'visibility: visible;'}
      }
    `;
  }}
`;

const Title = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingBold3}
    `;
  }}
`;

const Line = styled.hr<ActiveStateProps>`
  ${({ theme, isActive }) => {
    const { colors } = theme;
    return css`
      height: 3px;
      width: 100%;

      background-color: ${isActive ? colors.hyundaiPrimaryBlue : colors.black};
    `;
  }}
`;

const Price = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingBold4}
    `;
  }}
`;

const IconWrapper = styled.div`
  height: 100%;
  width: 80px;
  gap: 10px;

  display: flex;
  flex-direction: column;
  align-items: center;
`;

const ImageWrapper = styled.div`
  width: 100%;

  display: flex;
  justify-content: space-between;
`;

const Image = styled.img`
  width: 40px;
  height: 40px;
`;

const Text = styled.p<TextSizeProps>`
  ${({ theme, isBig }) => {
    const { fonts } = theme;
    return css`
      width: ${isBig ? '73px' : '48px'};
      height: 40px;

      text-align: center;
      ${fonts.captionMedium};
    `;
  }}
`;

export { Container, Title, Line, Price, ImageWrapper, Image, Text, IconWrapper };
