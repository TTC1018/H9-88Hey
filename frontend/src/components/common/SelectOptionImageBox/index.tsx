import * as style from './style';

interface SelectOptionImageBoxProps {
  image: string;
}

export function SelectOptionImageBox({ image }: SelectOptionImageBoxProps) {
  return (
    <>
      <style.Image src={image} />
      <style.Button>옵션 위치 보기</style.Button>
    </>
  );
}
