import { ForwardedRef } from 'react';

import styled from '@emotion/styled';
import { css } from '@emotion/react';

interface Props {
  ref: ForwardedRef<HTMLUListElement>;
}

export const Container = styled.div`
  width: 160px;
  height: 197px;
  top: 0;
  left: 0;

  position: absolute;

  border-radius: 8px;
  border: 0;
  background: rgba(35, 35, 35, 0.75);
`;

export const DescriptionWrapper = styled.ul<Props>`
  width: 160px;
  height: 140px;
  padding: 21px 15px 0 30px;

  overflow-y: scroll;
  ::-webkit-scrollbar {
    display: none;
  }

  list-style-type: disc;
  list-style-position: outside;
`;

export const DescriptionHover = styled.li`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      color: #fff;
      ${fonts.bodyRegular4}
    `;
  }}
`;
