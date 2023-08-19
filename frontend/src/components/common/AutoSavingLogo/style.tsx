import styled from '@emotion/styled';

export const Container = styled.div`
  animation: rotate_svg 0.8s linear infinite;

  @keyframes rotate_svg {
    100% {
      transform: rotate(-360deg);
    }
  }
`;
