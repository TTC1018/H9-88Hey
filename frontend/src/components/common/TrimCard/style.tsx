import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface ActiveStateProps {
  isActive: boolean;
}
const Container = styled.div<ActiveStateProps>`
  ${({ theme, isActive }) => {
    const { colors } = theme;
    return css`
      width: 391px;
      padding: 18px 23px 20px 23px;

      position: relative;

      color: ${isActive ? colors.hyundaiPrimaryBlue : colors.black};

      background: ${isActive ? 'rgba(56, 93, 162, 0.1)' : colors.hyundaiLightSand};
      border: 3px solid ${isActive ? colors.hyundaiPrimaryBlue : colors.hyundaiLightSand};
      border-radius: 8px;

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

const Wrapper = styled.div`
  width: 100%;
  margin-bottom: 22px;

  display: flex;
  justify-content: space-between;
`;

const Title = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingBold3}
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

const Description = styled.div`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyRegular3}
    `;
  }}
`;

const Line = styled.hr<ActiveStateProps>`
  ${({ theme, isActive }) => {
    const { colors } = theme;
    return css`
      width: 100%;
      height: 2px;
      margin: 10px 0;

      background-color: ${isActive ? colors.hyundaiPrimaryBlue : colors.black};
    `;
  }}
`;

const Info = styled.div`
  width: 100%;
  margin-bottom: 8px;

  display: flex;
  justify-content: space-between;
`;

const InfoTitle = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium3}
    `;
  }}
`;

const InfoContent = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyRegular3}
    `;
  }}
`;
export { Container, Wrapper, Title, Price, Description, Info, InfoTitle, InfoContent, Line };
