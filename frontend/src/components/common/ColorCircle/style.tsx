import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface Props {
  imageUrl: string;
}

export const Container = styled.div<Props>`
  ${({ imageUrl }) => {
    return css`
      width: 16px;
      height: 16px;

      flex-shrink: 0;

      background-image: url(${imageUrl});
      background-position: center;
      border-radius: 50%;
    `;
  }}
`;
