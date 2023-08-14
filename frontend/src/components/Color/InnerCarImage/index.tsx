import * as Styled from './style';

interface InnerCarImageProps {
  color: string;
}

export function InnerCarImage({ color }: InnerCarImageProps) {
  return (
    <Styled.Container>
      <Styled.MainImage src={`https://www.hyundai.com/contents/vr360/LX06/interior/${color}/img-interior.png`} />
    </Styled.Container>
  );
}
