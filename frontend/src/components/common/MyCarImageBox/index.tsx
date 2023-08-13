import * as Styled from './style';

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
    <Styled.Container>
      {hasOption ? (
        <>
          <Styled.Wrapper>
            {images.map((image, index) => (
              <Styled.SubImage
                src={image}
                key={image}
                isActive={index === selectedIndex}
                onClick={onClick(index)}
                alt="서브 이미지"
              />
            ))}
          </Styled.Wrapper>
          <Styled.Image src={images[selectedIndex]} alt="메인 이미지" />
        </>
      ) : (
        <Styled.Image src={images} alt="메인 이미지" />
      )}
    </Styled.Container>
  );
}
