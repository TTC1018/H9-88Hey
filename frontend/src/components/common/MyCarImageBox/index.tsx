import * as style from './style';

interface MyCarImageBoxProps {
  images: string[];
  hasOption: boolean;
  selectedIndex: number;
  onClick: (index: number) => void;
}
export function MyCarImageBox({ images, hasOption, selectedIndex, onClick }: MyCarImageBoxProps) {
  return (
    <style.Container>
      {hasOption && (
        <style.Wrapper>
          {images.map((image, index) => (
            <style.SubImage
              src={image}
              key={image}
              isActivate={index === selectedIndex}
              onClick={() => onClick(index)}
            />
          ))}
        </style.Wrapper>
      )}
      <style.Image src={images[selectedIndex]} alt="이미지" />
    </style.Container>
  );
}
