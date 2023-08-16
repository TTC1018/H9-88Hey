import styled from '@emotion/styled';
import { css } from '@emotion/react';

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

export const Wrapper = styled.ul`
  padding: 21px 15px 0 30px;
  overflow: hidden;

  list-style-type: disc;
  list-style-position: outside;
`;

export const DescriptionHover = styled.li`
  ${({ theme }) => {
    const { fonts } = theme;

    return css`
      overflow: hidden;

      color: #fff;
      ${fonts.bodyRegular4}
      text-overflow: ellipsis;
      white-space: nowrap;
    `;
  }}
`;
