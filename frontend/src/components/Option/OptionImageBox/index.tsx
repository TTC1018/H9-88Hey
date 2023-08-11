import * as style from './style';

interface OptionImageBoxProps {
  imageUrl: string;
}

export function OptionImageBox({ imageUrl }: OptionImageBoxProps) {
  return (
    <style.Container>
      <style.Image src={imageUrl} />
    </style.Container>
  );
}
