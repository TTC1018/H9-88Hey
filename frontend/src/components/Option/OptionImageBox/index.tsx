import * as Styled from './style';

interface OptionImageBoxProps {
  imageUrl: string;
}

export function OptionImageBox({ imageUrl }: OptionImageBoxProps) {
  return (
    <Styled.Container>
      <Styled.Image src={imageUrl} />
    </Styled.Container>
  );
}
