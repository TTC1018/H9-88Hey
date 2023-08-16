import * as Styled from './style';

interface Props {
  title: string;
  date: string;
  trimOptions: string;
  exteriorColor: string;
  interiorColor: string;
  review: string;
  imageUrl: string;
}
export function DetailHeader({ title, date, trimOptions, exteriorColor, interiorColor, review, imageUrl }: Props) {
  return (
    <Styled.Container>
      <Styled.TextWrapper>
        <Styled.TextBox>
          <Styled.Enclosure>
            <Styled.Title>{title}</Styled.Title>
            <Styled.SubTitle>{trimOptions}</Styled.SubTitle>
          </Styled.Enclosure>
          <Styled.Date>{date}</Styled.Date>
        </Styled.TextBox>
        <Styled.ColorBox>
          <Styled.BodyText>외장</Styled.BodyText>
          <Styled.ColorText>{exteriorColor}</Styled.ColorText>
          <Styled.BodyText>내장</Styled.BodyText>
          <Styled.ColorText>{interiorColor}</Styled.ColorText>
        </Styled.ColorBox>
        <Styled.Line />
        <Styled.Review>{review}</Styled.Review>
      </Styled.TextWrapper>
      <Styled.Image src={imageUrl} alt={'image'} />
    </Styled.Container>
  );
}
