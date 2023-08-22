import LoadingGif from '/public/assets/Loading.gif';

import * as Styled from './style';

export function Loading() {
  return (
    <Styled.Container>
      <Styled.Image src={LoadingGif} />
    </Styled.Container>
  );
}
