import { css } from '@emotion/react';
import styled from '@emotion/styled';

export const Container = styled.div`
  gap: 16px;

  display: flex;
  flex-direction: column;
`;

export const TextWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const TextBox = styled.div`
  gap: 5px;

  display: flex;
  align-items: center;
`;

export const Title = styled.span`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyRegular2}
    `;
  }}
`;

export const SubTitle = styled.span`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      ${fonts.bodyRegular4}
      color:${colors.mediumGray};
    `;
  }}
`;
export const TextArea = styled.textarea`
  ${({ theme }) => {
    const { fonts, colors } = theme;
    return css`
      width: 354px;
      height: 210px;
      padding: 16px 20px;

      ${fonts.bodyRegular3};
      vertical-align: top;
      text-align: left;

      background-color: ${colors.hyundaiLightSand};
      border: 0;

      resize: none;

      ::placeholder {
        color: ${colors.mediumGray};
      }
    `;
  }}
`;
