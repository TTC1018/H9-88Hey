import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const HeaderWrapper = styled.div`
  top: 0;

  position: sticky;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  background-color: #fff;
`;

export const ReviewWrapper = styled.div`
  width: 1024px;
  margin-top: 50px;

  display: flex;
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
