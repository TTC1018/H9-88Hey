import styled from '@emotion/styled';

export const Container = styled.div`
  width: 100vw;
  height: 100vh;

  display: flex;
  justify-content: center;
  align-items: center;
`;

export const SliderWrapper = styled.div`
  width: 1024px;

  position: relative;

  overflow: hidden;
`;

export const SliderBox = styled.div`
  width: 1024px;

  display: flex;
`;

export const PrevButton = styled.div`
  left: -10px;
  top: 50px;

  position: absolute;

  z-index: 1;
`;

export const Slide = styled.div`
  width: 1024px;
`;
