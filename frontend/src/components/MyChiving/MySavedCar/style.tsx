import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Contianer = styled.div`
  margin-top: 25px;
  gap: 15px;

  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
`;

export const MyCarBox = styled.div`
  gap: 20px;

  display: grid;
  grid-template-columns: repeat(2, 1fr);
  position: relative;
`;

export const EmptyBox = styled.div`
  width: 506px;
  height: 239px;
`;

export const Wrapper = styled.div`
  gap: 20px;

  display: flex;
`;

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
