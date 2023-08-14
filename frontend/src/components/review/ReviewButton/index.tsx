import * as Styled from './style';

interface Props {
  onClick: () => void;
  isActive: boolean;
  text: string;
}
export function ReviewButton({ onClick, isActive, text }: Props) {
  return (
    <Styled.Container onClick={onClick} isActive={isActive}>
      {text}
    </Styled.Container>
  );
}
