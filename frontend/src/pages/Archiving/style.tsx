import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 100vw;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const ReviewWrapper = styled.div`
  width: 1024px;
  gap: 30px;

  display: flex;
`;

export const ReviewBox = styled.div`
  gap: 30px;

  display: grid;
  grid-template-columns: repeat(2, 1fr);
`;

export const InfoBox = styled.div`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      width: 100%;
      margin-top: 50px;

      display: flex;
      justify-content: center;

      ${fonts.bodyMedium1}
    `;
  }}
`;

export const CardBox = styled.div``;

export const Enclosure = styled.div``;
