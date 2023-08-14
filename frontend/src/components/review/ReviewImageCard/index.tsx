import * as Styled from './style';

interface Props {
  image: string;
  title: string;
  subTitle: string;
  price: number;
  interiorColor: string;
  exteriorColor: string;
}
export function ReviewImageCard({ image, title, subTitle, price, interiorColor, exteriorColor }: Props) {
  return (
    <Styled.Container>
      <Styled.Svg width="419" height="416" viewBox="0 0 419 416" fill="none" xmlns="http://www.w3.org/2000/svg">
        <path
          fillRule="evenodd"
          clipRule="evenodd"
          d="M0 8.00001C0 3.58173 3.58172 0 8 0H411C415.418 0 419 3.58172 419 8V303.573C419 305.918 417.587 307.989 415.675 309.347C409.535 313.706 405.444 321.5 405.444 330.391C405.444 339.281 409.535 347.076 415.675 351.435C417.587 352.792 419 354.863 419 357.208V408C419 412.418 415.418 416 411 416H8.00001C3.58173 416 0 412.418 0 408V357.208C0 354.863 1.41281 352.792 3.32481 351.435C9.46527 347.076 13.5558 339.281 13.5558 330.391C13.5558 321.5 9.46527 313.706 3.32481 309.347C1.41281 307.989 0 305.918 0 303.574V8.00001Z"
          fill="#F6F3F2"
        />
      </Styled.Svg>
      <Styled.Image src={image} alt={title} />
      <Styled.Wrapper>
        <Styled.Title>{title}</Styled.Title>
        <Styled.Regular>{subTitle}</Styled.Regular>
        <Styled.PriceBox>
          <Styled.Medium>{price.toLocaleString()}원</Styled.Medium>
        </Styled.PriceBox>
        <Styled.Line />
        <Styled.ColorBox>
          <Styled.Medium>외장</Styled.Medium>
          <Styled.Regular>{exteriorColor}</Styled.Regular>
        </Styled.ColorBox>
        <Styled.ColorBox>
          <Styled.Medium>내장</Styled.Medium>
          <Styled.Regular>{interiorColor}</Styled.Regular>
        </Styled.ColorBox>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
