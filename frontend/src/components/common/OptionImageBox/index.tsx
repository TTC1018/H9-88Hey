import * as style from './style';

interface OptionImageBoxProps {
  image: string;
}

export function OptionImageBox({ image }: OptionImageBoxProps) {
  return (
    <>
      <style.Image src={image} />
      <style.Button>옵션 위치 보기</style.Button>
    </>
  );
}
