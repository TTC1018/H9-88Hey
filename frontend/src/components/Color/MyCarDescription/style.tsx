import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 100%;
`;

export const Wrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const Title = styled.div`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      gap: 7px;

      display: flex;
      align-items: end;

      ${fonts.headingBold1}
    `;
  }}
`;

export const SubTitle = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyRegular3};
    `;
  }}
`;

export const Price = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingBold2}
    `;
  }}
`;

export const Line = styled.hr`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 100%;
      height: 5px;
      margin: 8px 0;

      background-color: ${colors.black};
    `;
  }}
`;

export const TagWrapper = styled.ul`
  margin-top: 14px;
  gap: 8px;

  display: flex;
`;
