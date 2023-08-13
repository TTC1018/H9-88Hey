import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface ContainerProps {
  isInnerColorIcon: boolean;
}

export const Container = styled.div<ContainerProps>`
  ${({ isInnerColorIcon }) => {
    return css`
      position: absolute;
      top: -6px;
      right: ${isInnerColorIcon ? '2px' : '-6px'};
    `;
  }}
`;
