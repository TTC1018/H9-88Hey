import styled from '@emotion/styled';

const Container = styled.div`
  width: 1100px;

  height: 490px;

  display: flex;
  justify-content: space-around;
`;

const ImageWrapper = styled.div`
  height: 100%;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

const CardWrapper = styled.div`
  height: 100%;
  gap: 25px;

  display: flex;
  flex-direction: column;
  justify-content: start;
`;

const Box = styled.div``;

export { Container, ImageWrapper, CardWrapper, Box };
