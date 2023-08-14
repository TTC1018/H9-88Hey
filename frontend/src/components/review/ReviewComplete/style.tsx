import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  height: 100%;
  width: 1024px;

  display: flex;
  flex-direction: column;
  justify-content: flex-end;
  align-items: center;
`;

export const Wrapper = styled.div`
  gap: 20px;
  padding: 100px;

  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const Title = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingBold1}
    `;
  }}
`;

export const Description = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium4}
    `;
  }}
`;
