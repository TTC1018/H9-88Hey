import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface Props {
  isActive: boolean;
}

export const Conatiner = styled.div`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      height: 135px;
      width: 100vw;

      display: flex;
      align-items: center;
      justify-content: center;

      background-color: ${colors.hyundaiNeutral};
    `;
  }}
`;

export const Wrapper = styled.div`
  width: 1024px;
  gap: 10px;

  display: flex;
  justify-content: center;
  flex-wrap: wrap;
`;

export const Option = styled.div<Props>`
  ${({ theme, isActive }) => {
    const { fonts, colors } = theme;
    return css`
      padding: 4px 12px;
      gap: 10px;

      display: inline-flex;
      justify-content: center;
      align-items: center;

      ${fonts.bodyRegular3}
      color: ${isActive ? colors.hyundaiNeutral : colors.darkGray};

      border-radius: 4px;
      border: 0.5px solid ${isActive ? '#385DA2' : colors.lightGray};
      background-color: ${isActive ? '#385DA2' : '#fff'};

      cursor: pointer;
      &:hover {
        font-weight: bold;
        color: ${colors.hyundaiNeutral};

        border-color: #385da2;
        background-color: #385da2;

        cursor: pointer;
      }
    `;
  }}
`;
