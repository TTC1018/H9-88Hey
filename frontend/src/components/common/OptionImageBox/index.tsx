import * as style from './style';

interface OptionImageBoxProps {
  imageUrl: string;
}

export function OptionImageBox({ imageUrl }: OptionImageBoxProps) {
  return (
    <style.Container>
      <style.Image src={imageUrl} />
      <style.Button>옵션 위치 보기</style.Button>
    </style.Container>
  );
}
