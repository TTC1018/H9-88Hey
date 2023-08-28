import UpArrow from '/public/assets/icons/arrow_down.svg';

import * as Styled from './style';

interface Props {
  onClick: () => void;
}
export function ScrollTopButton({ onClick }: Props) {
  return (
    <Styled.Container onClick={onClick}>
      <Styled.Image src={UpArrow} />
    </Styled.Container>
  );
}
