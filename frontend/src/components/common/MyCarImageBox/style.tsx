import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface ActiveStateProps {
  isActive: boolean;
}

const Container = styled.div`
  gap: 8px;

  display: flex;
`;

const Image = styled.img`
  width: 620px;
  height: 397px;

  border-radius: 4px;

  object-fit: fill;
`;

const Wrapper = styled.div`
  gap: 8px;

  display: flex;
  flex-direction: column;
`;

const SubImage = styled.img<ActiveStateProps>`
  ${({ theme, isActive }) => {
    const { colors } = theme;
    return css`
      width: 56px;
      height: 56px;

      border-radius: 8px;
      border: ${isActive ? `2px solid #00AAD2` : `1px solid ${colors.lightGray}`};

      object-fit: fill;
    `;
  }}
`;

export { Container, Image, Wrapper, SubImage };
