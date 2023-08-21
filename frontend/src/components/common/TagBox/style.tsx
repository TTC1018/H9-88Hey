import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 100%;
  height: 30px;
  gap: 4px;

  display: flex;
`;

export const Tag = styled.div`
  ${({ theme }) => {
    const { colors, fonts } = theme;

    return css`
      height: 30px;
      padding: 4px 8px;

      display: inline-flex;
      justify-content: center;
      align-items: center;

      ${fonts.bodyRegular3};
      colors: ${colors.black};

      border-radius: 8px;
      background-color: ${colors.hyundaiLightSand};

      opacity: 0;
      animation: fadeIn 0.5s ease-in-out forwards;
      @keyframes fadeIn {
        from {
          opacity: 0;
        }
        to {
          opacity: 1;
        }
      }
    `;
  }}
`;
