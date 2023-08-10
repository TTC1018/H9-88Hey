import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 100vw;
  height: 100vh;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const Wrapper = styled.div`
  height: 500px;
  gap: 15px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const Image = styled.img`
  width: 166px;
  height: 23px;
`;

export const Head = styled.h2`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingBold1}
    `;
  }}
`;

export const Body = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium2}
    `;
  }}
`;
