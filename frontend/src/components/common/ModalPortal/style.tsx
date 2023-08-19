import styled from '@emotion/styled';

export const Container = styled.div`
  width: 100vw;
  height: 100vh;
  top: 0;
  left: 0;

  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;

  background: rgba(35, 35, 35, 0.6);
  backdrop-filter: blur(2px);
  z-index: 999;
`;
