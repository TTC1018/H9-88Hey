import { keyframes } from '@emotion/react';
import styled from '@emotion/styled';

const rotate_svg = keyframes`
    100% {
        transform: rotate(-360deg);
    }
`;

export const Container = styled.div`
  animation: ${rotate_svg} 0.8s linear infinite;
`;
