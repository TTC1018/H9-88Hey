import * as style from './style';

interface OptionImageBoxProps {
  imageURL: string;
}

export function OptionImageBox({ imageURL }: OptionImageBoxProps) {
  return (
    <style.Container>
      <style.Image src={imageURL} />
      <style.Button>옵션 위치 보기</style.Button>
    </style.Container>
  );
}
