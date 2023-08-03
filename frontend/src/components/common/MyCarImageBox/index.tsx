import * as style from './style';

interface MyCarImageBoxPropsWithOption {
  images: string[];
  hasOption: true;
  selectedIndex: number;
  onClick: (index: number) => () => void;
}
interface MyCarImageBoxPropsWithoutOption {
  images: string;
  hasOption: false;
  selectedIndex?: never;
  onClick?: never;
}
type MyCarImageBoxProps = MyCarImageBoxPropsWithOption | MyCarImageBoxPropsWithoutOption;
export function MyCarImageBox({ images, hasOption, selectedIndex, onClick }: MyCarImageBoxProps) {
  return (
    <style.Container>
      {hasOption ? (
        <>
          <style.Wrapper>
            {images.map((image, index) => (
              <style.SubImage src={image} key={image} isActive={index === selectedIndex} onClick={onClick(index)} />
            ))}
          </style.Wrapper>
          <style.Image src={images[selectedIndex]} alt="이미지" />
        </>
      ) : (
        <style.Image src={images} alt="이미지" />
      )}
    </style.Container>
  );
}
