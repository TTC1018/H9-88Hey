import { css } from '@emotion/react';
import styled from '@emotion/styled';

const Container = styled.nav`
  ${({ theme }) => {
    const { colors } = theme;
    return css`
      width: 100%;
      padding: 15px 100px;

      display: flex;
      justify-content: space-between;

      background-color: ${colors.hyundaiLightSand};
    `;
  }}
`;

const Wrapper = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;

interface OrderNumberProps {
  isDisplay: boolean;
  isCurrent: boolean;
}

const OrderNumber = styled.span<OrderNumberProps>`
  ${({ theme, isDisplay, isCurrent }) => {
    const { fonts, colors } = theme;
    return css`
      padding: 2px 8px;

      display: ${isDisplay ? 'block' : 'none'};
      ${fonts.bodyMedium4}
      color: #fff;

      background-color: ${isCurrent ? colors.black : colors.mediumGray};
      border-radius: 50%;
    `;
  }}
`;

const OrderTitle = styled.h3`
  ${({ theme }) => {
    const { fonts } = theme;
    return css`
      ${fonts.bodyMedium2}
    `;
  }}
`;

const OrderBox = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;

const Order = styled.div`
  display: flex;
  align-items: center;
  gap: 10px;
`;

interface OrderTextProps {
  isCurrent: boolean;
}

const OrderText = styled.span<OrderTextProps>`
  ${({ theme, isCurrent }) => {
    const { colors, fonts } = theme;
    return css`
      color: ${isCurrent ? colors.black : colors.mediumGray};
      ${fonts.bodyMedium3}
    `;
  }}
`;

export { Container, Wrapper, OrderNumber, OrderTitle, Order, OrderBox, OrderText };
