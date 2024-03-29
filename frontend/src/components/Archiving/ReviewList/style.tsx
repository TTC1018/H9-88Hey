import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  gap: 30px;

  display: grid;
  grid-template-columns: repeat(2, 1fr);
  grid-auto-rows: 5px;
`;

export const Wrapper = styled.div``;

export const Loading = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 500px;
      height: 300px;

      background-color: ${colors.hyundaiLightSand};

      animation: skeleton-gradient 1.8s infinite ease-in-out;
      @keyframes skeleton-gradient {
        0% {
          background-color: rgba(165, 165, 165, 0.1);
        }

        50% {
          background-color: rgba(165, 165, 165, 0.3);
        }

        100% {
          background-color: rgba(165, 165, 165, 0.1);
        }
      }
    `;
  }}
`;
