import Bar from '/public/assets/icons/bar.svg';

import * as Styled from './style';

interface Props {
  imageUrl: string;
  name: string;
  price: number;
  subOptions: string[];
  index: number;
}

export function OptionCard({ imageUrl, name, price, subOptions, index }: Props) {
  return (
    <Styled.Container hasMargin={index % 2 === 0}>
      <Styled.Bar />
      <Styled.CardWrapper>
        <Styled.ImageWrapper>
          <Styled.Image src={imageUrl} alt="옵션 이미지" />
        </Styled.ImageWrapper>
        <Styled.TextWrapper>
          <Styled.TitleWrapper>
            <Styled.Title>{name}</Styled.Title>
            <Styled.TitleBarWrapper>
              <Styled.TitleBar src={Bar} alt="타이틀 바" />
            </Styled.TitleBarWrapper>
            <Styled.Title>{price.toLocaleString()}원</Styled.Title>
          </Styled.TitleWrapper>
          <Styled.Description>{subOptions.join(' / ')}</Styled.Description>
        </Styled.TextWrapper>
      </Styled.CardWrapper>
    </Styled.Container>
  );
}
