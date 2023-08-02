import * as style from './MyCarImageBox.style';

interface MyCarImageBoxProps {
  selectedIndex: number;
  images: string[];
  hasOption: boolean;
}
export function MyCarImageBox({ selectedIndex, images, hasOption }: MyCarImageBoxProps) {
  return (
    <style.Container>
      {hasOption && (
        <style.SubImageWrapper>
          {images.map((image, idx) => (
            <style.SubImage src={image} key={image} isActivate={idx === selectedIndex} />
          ))}
        </style.SubImageWrapper>
      )}
      <style.Image src={images[selectedIndex]} alt="이미지" />
    </style.Container>
  );
}
