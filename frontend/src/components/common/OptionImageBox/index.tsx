import * as Styled from './style';

interface OptionImageBoxProps {
  imageUrl: string;
}

export function OptionImageBox({ imageUrl }: OptionImageBoxProps) {
  return (
    <Styled.Container>
      <Styled.Image src={imageUrl} />
      <Styled.Button>옵션 위치 보기</Styled.Button>
    </Styled.Container>
  );
}
