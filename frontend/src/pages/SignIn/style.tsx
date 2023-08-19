import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const Container = styled.div`
  width: 100vw;
  height: 100vh;
  gap: 50px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const HyundaiLogo = styled.img``;

export const Form = styled.div`
  gap: 5px;

  display: flex;
  flex-direction: column;
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

      background-color: ${colors.hyundaiPrimaryBlue500};
      transition: background-color 0.2s ease;

      &:hover {
        background-color: ${colors.hyundaiPrimaryBlue};
      }
    `;
  }}
`;
