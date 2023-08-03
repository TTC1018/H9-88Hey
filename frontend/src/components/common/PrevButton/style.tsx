import { css } from '@emotion/react';
import styled from '@emotion/styled/macro';

interface ContainerProps {
  width: number;
  height: number;
}

const Container = styled.button<ContainerProps>`
  ${({ width, height }) => {
    return css`
        width: ${width},
        height: ${height}
    `;
  }}
`;

export { Container };
