import { css } from '@emotion/react';
import styled from '@emotion/styled';

const Container = styled.div`
  width: 100%;
`;

const Wrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const Title = styled.p`
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

const SubTitle = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyRegular3};
    `;
  }}
`;

const Price = styled.p`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.headingBold2}
    `;
  }}
`;

const Line = styled.hr`
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

const TagWrapper = styled.ul`
  margin-top: 14px;
  gap: 8px;

  display: flex;
`;

const Tag = styled.li`
  ${({ theme }) => {
    const { colors, fonts } = theme;
    return css`
      padding: 4px 8px;

      display: inline-flex;
      justify-content: center;
      align-items: center;

      ${fonts.bodyRegular3};

      border-radius: 8px;
      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

export { Container, Wrapper, Title, SubTitle, Price, Line, TagWrapper, Tag };
