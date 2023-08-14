import * as Styled from './style';

interface InnerCarImageProps {
  imageUrl: string;
}

export function InnerCarImage({ imageUrl }: InnerCarImageProps) {
  return (
    <Styled.Container>
      <Styled.MainImage src={imageUrl} />
    </Styled.Container>
  );
}
