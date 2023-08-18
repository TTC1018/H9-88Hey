import * as Styled from './style';
import LoadingGif from '@/assets/Loading.gif';

export function Loading() {
  return (
    <Styled.Container>
      <Styled.Image src={LoadingGif} />
    </Styled.Container>
  );
}
