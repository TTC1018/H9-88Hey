import { Link } from 'react-router-dom';

import styled from '@emotion/styled';
import { css } from '@emotion/react';

interface Props {
  isShow: boolean;
}

export const Container = styled.div`
  width: 100vw;
  height: 100vh;
  gap: 45px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const HyundaiLogo = styled.img``;

export const Form = styled.form`
  gap: 5px;

  display: flex;
  flex-direction: column;
`;

export const Alert = styled.p<Props>`
  ${({ theme, isShow }) => {
    const { colors, fonts } = theme;

    return css`
      color: ${colors.alertPrimary};
      ${fonts.bodyRegular3};

      visibility: ${isShow ? 'visible' : 'hidden'};
    `;
  }}
`;

export const Input = styled.input`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      width: 400px;
      height: 40px;
      padding: 10px 12px;

      ${fonts.bodyRegular3};

      border: 1px solid ${colors.lightGray};
      border-radius: 8px;

      &::placeholder {
        color: ${colors.mediumGray};
      }

      &:focus {
        border: 1px solid ${colors.hyundaiPrimaryBlue500};
        outline: none;
        -webkit-appearance: none;
      }

      transition: border 0.2s ease;
    `;
  }}
`;

export const Button = styled.button`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      width: 400px;
      height: 55px;
      margin-top: 5px;

      color: ${colors.hyundaiNeutral};
      ${fonts.headingBold4};

      border-radius: 8px;
      background-color: ${colors.hyundaiPrimaryBlue500};
      transition: background-color 0.2s ease;

      &:hover {
        background-color: ${colors.hyundaiPrimaryBlue};
      }
    `;
  }}
`;

export const Flex = styled.div`
  width: 400px;

  display: flex;
  justify-content: space-between;
`;

export const LinkWrapper = styled(Link)`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      margin-top: 20px;

      ${fonts.bodyRegular3};

      color: ${colors.hyundaiPrimaryBlue500};
      text-decoration: none;

      cursor: pointer;

      &:hover {
        text-decoration: underline;
      }
    `;
  }}
`;
