import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface ActivateStateProps {
  isActivate: boolean;
}

const Container = styled.div`
  display: flex;
  gap: 8px;
`;

const Image = styled.img`
  width: 620px;
  height: 397px;

  object-fit: fill;
`;

const SubImageWrapper = styled.div`
  display: flex;
  flex-direction: column;
  gap: 8px;
`;

const SubImage = styled.img<ActivateStateProps>`
  ${({ theme, isActivate }) => {
    const { colors } = theme;
    return css`
      width: 56px;
      height: 56px;

      border-radius: 8px;
      border: ${isActivate ? `2px solid #00AAD2` : `1px solid ${colors.lightGray}`};

      object-fit: fill;
    `;
  }}
`;

export { Container, Image, SubImageWrapper, SubImage };
