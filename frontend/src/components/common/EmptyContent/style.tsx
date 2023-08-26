import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 1024px;
  height: 100%;
  gap: 30px;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const Wrapper = styled.div`
  margin-top: 20px;
  gap: 10px;

  display: flex;
  flex-direction: column;
`;

export const Text = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      padding-left: 20px;

      ${fonts.bodyMedium1};
    `;
  }}
`;
