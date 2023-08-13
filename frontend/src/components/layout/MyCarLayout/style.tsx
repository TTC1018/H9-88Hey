import styled from '@emotion/styled';
import { css } from '@emotion/react';

interface Props {
  isFull: boolean;
}

const Container = styled.div<Props>`
  ${({ isFull }) => {
    return css`
      width: 100vw;
      height: ${!isFull && '100vh'};

      display: flex;
      justify-content: center;
      align-items: center;
      flex-direction: column;
    `;
  }}
`;

const Wrapper = styled.div<Props>`
  ${({ isFull }) => {
    return css`
      width: ${isFull ? '100%' : '1024px'};

      display: flex;
      flex-grow: 1;
    `;
  }}
`;

export { Container, Wrapper };
