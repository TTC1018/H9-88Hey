import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  height: 410px;
  width: 520px;
  padding: 10px 20px 0 0;

  overflow: auto;
`;

export const Wrapper = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      padding: 10px 0;

      display: flex;

      border-top: 1px solid ${colors.lightGray};
      border-bottom: 1px solid ${colors.lightGray};
    `;
  }}
`;

export const TextBox = styled.div`
  width: 384px;
  padding: 0 10px;

  display: flex;
  flex-direction: column;
  justify-content: center;
`;

export const Encloser = styled.div``;

export const Image = styled.img`
  width: 100px;
  height: 82px;
`;

export const Medium = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium2};
    `;
  }}
`;

export const Description = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyRegular4}
    `;
  }}
`;
