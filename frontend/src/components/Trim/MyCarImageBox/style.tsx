import { css } from '@emotion/react';
import styled from '@emotion/styled';

interface ActiveStateProps {
  isActive: boolean;
}

const Container = styled.div`
  gap: 8px;

  display: flex;
  position: relative;
`;

const Image = styled.img`
  width: 620px;
  height: 397px;

  border-radius: 4px;

  object-fit: cover;

  opacity: 0;
  animation: fadeIn 0.5s ease-in-out forwards;
  @keyframes fadeIn {
    from {
      opacity: 0;
    }
    to {
      opacity: 1;
    }
  }
`;

const Wrapper = styled.div`
  gap: 8px;
  left: -80px;

  display: flex;
  flex-direction: column;
  position: absolute;
`;

const SubImage = styled.img<ActiveStateProps>`
  ${({ theme, isActive }) => {
    const { colors } = theme;
    return css`
      width: 56px;
      height: 56px;

      border-radius: 8px;
      border: 2px solid ${isActive ? '#00AAD2' : colors.lightGray};

      object-fit: cover;

      cursor: pointer;
      opacity: 0;
      animation: fadeIn 0.5s ease-in-out forwards;
      @keyframes fadeIn {
        from {
          opacity: 0;
        }
        to {
          opacity: 1;
        }
      }
    `;
  }}
`;

export { Container, Image, Wrapper, SubImage };
