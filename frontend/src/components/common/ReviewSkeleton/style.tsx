import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 1024px;
  height: 400px;
  gap: 30px;

  display: flex;
  flex-direction: column;
`;

export const TagWrapper = styled.div`
  gap: 30px;

  display: flex;
`;

export const TagSkeleton = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      height: 300px;
      width: 500px;

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
