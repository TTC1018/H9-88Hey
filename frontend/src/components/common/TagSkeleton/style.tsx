import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  gap: 8px;

  display: flex;
`;

export const TagSkeleton = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      height: 30px;
      width: 100px;

      border-radius: 8px;
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
