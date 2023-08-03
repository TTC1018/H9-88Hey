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

      color: ${isActive ? colors.hyundaiPrimaryBlue : colors.black};

      background: ${isActive ? 'rgba(56, 93, 162, 0.1)' : colors.hyundaiLightSand};
      border: 2px solid ${isActive ? colors.hyundaiPrimaryBlue : colors.hyundaiLightSand};
      border-radius: 8px;
    `;
  }}
`;

const Wrapper = styled.div`
  width: 100%;
  margin-bottom: 22px;

  display: flex;
  justify-content: space-between;
`;

const Title = styled.div`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingBold3}
    `;
  }}
`;

const Price = styled.div`
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
      margin: 20px 0;

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

const InfoTitle = styled.div`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium3}
    `;
  }}
`;

const InfoContent = styled.div`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyRegular3}
    `;
  }}
`;
export { Container, Wrapper, Title, Price, Description, Info, InfoTitle, InfoContent, Line };
