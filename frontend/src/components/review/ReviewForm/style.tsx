import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 1024px;
  height: 635px;

  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const TitleWrapper = styled.p`
  width: 850px;
  margin-top: 44px;
`;

export const Title = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      width: 360px;

      display: block;

      ${fonts.headingBold1};
    `;
  }}
`;

export const Wrapper = styled.div`
  height: 70%;
  padding-top: 30px;
  gap: 61px;

  display: flex;
`;

export const TagBox = styled.div`
  width: 381px;

  display: flex;
  flex-direction: column;
`;

export const TextBox = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      padding-left: 46px;

      display: flex;
      flex-direction: column;
      justify-content: space-between;

      border-left: 1px solid ${colors.lightGray};
    `;
  }}
`;

export const Image = styled.img`
  margin: 24px 0;
  width: 381px;
  height: 348px;

  border-radius: 8px;
`;

export const SubTitle = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      margin-bottom: 5px;

      ${fonts.headingBold4};
    `;
  }}
`;

export const Line = styled.hr`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      height: 4px;
      width: 100%;
      margin: 0;

      background-color: ${colors.darkGray};
    `;
  }}
`;

export const Description = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      margin-top: 10px;

      ${fonts.bodyRegular3};
    `;
  }}
`;
