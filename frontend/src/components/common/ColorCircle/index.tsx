import * as Styled from './style';

interface Props {
  imageUrl: string;
}

export function ColorCircle({ imageUrl }: Props) {
  return <Styled.Container imageUrl={imageUrl} />;
}
