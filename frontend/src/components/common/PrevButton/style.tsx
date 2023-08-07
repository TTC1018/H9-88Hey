import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface ContainerProps {
  width: number;
  height: number;
  isShow: boolean;
}

const Container = styled.button<ContainerProps>`
  ${({ width, height, isShow }) => {
    return css`
      width: ${width};
      height: ${height};

      visibility: ${isShow ? 'visible' : 'hidden'};
      // display: ${isShow ? 'block' : 'none'};
    `;
  }}
`;

export { Container };
