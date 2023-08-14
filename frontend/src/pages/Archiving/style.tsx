import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface Props {
  isActive: boolean;
}
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

  display: flex;
  flex-direction: column;
`;

export const CarSearchBar = styled.div`
  width: 1024px;
  height: 50px;

  display: flex;
  justify-content: space-between;
  align-items: end;
`;

export const Car = styled.p<Props>`
  ${({ theme, isActive }) => {
    const { colors, fonts } = theme;
    return css`
      margin-bottom: 2px;

      position: relative;

      ${fonts.headingBold3}
      color: ${isActive ? colors.black : '#CECECE'};

      &::after {
        height: ${isActive ? '5px' : '0px'};
        bottom: -2px;
        left: 0;
        right: 0;

        position: absolute;

        background-color: #000;

        transform-origin: center;
        ${isActive ? 'transform: scaleX(1)' : 'transform: scaleX(0)'};
        transition: transform 0.25s ease-out;

        content: '';
      }

      &:hover {
        font-weight: bold;
        color: ${colors.black};

        cursor: pointer;
      }
      &:hover:after {
        height: 5px;

        transform: scaleX(1);
        transform-origin: center;
      }
    `;
  }}
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
