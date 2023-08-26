import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  width: 1024px;
  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

export const Title = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      width: 850px;

      ${fonts.headingRegular1};
    `;
  }}
`;

export const Focus = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingBold1};
    `;
  }}
`;

export const Wrapper = styled.div`
  gap: 61px;

  display: flex;
`;

export const ImageBox = styled.div`
  width: 381px;

  display: flex;
  flex-direction: column;
`;

export const TagBox = styled.div`
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

  object-fit: cover;
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
