import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface ActivateStateProps {
  isActivate: boolean;
}
const Container = styled.div<ActivateStateProps>`
  ${({ theme, isActivate }) => {
    const { colors } = theme;
    return css`
      width: 391px;
      height: 100%;
      padding: 18px 23px 28px 23px;

      color: ${isActivate ? colors.hyundaiPrimaryBlue : colors.black};

      background: ${isActivate ? 'rgba(56, 93, 162, 0.1)' : colors.hyundaiLightSand};
      border: ${isActivate && `2px solid ${colors.hyundaiPrimaryBlue}`};
      border-radius: 8px;
    `;
  }}
`;

const Header = styled.div`
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

const Line = styled.hr<ActivateStateProps>`
  ${({ theme, isActivate }) => {
    const { colors } = theme;
    return css`
      width: 100%;
      height: 2px;
      margin: 20px 0;

      background-color: ${isActivate ? colors.hyundaiPrimaryBlue : colors.black};
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

export { Container, Header, Title, Price, Description, Info, InfoTitle, InfoContent, Line };
