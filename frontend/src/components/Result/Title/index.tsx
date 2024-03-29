import { hasJongSeong } from '@/utils';

import Arrow from '/public/assets/icons/arrow_right.svg';

import * as Styled from './style';

interface Props {
  krName: string;
  enName: string;
  imageUrl: string;
}

export function Title({ krName, enName, imageUrl }: Props) {
  return (
    <Styled.Container>
      <Styled.Box>
        <Styled.TitleWrapper>
          <Styled.Title>{enName.toUpperCase()}</Styled.Title>
          <Styled.TitleLine />
          <Styled.Message>
            나의 {krName}
            {hasJongSeong(krName) ? '이' : '가'} 완성되었어요!
          </Styled.Message>
          <Styled.DescriptionWrapper>
            <Styled.Description>완성된 차량은 마이페이지</Styled.Description>
            <Styled.RightArrow src={Arrow} />
            <Styled.Description> 마이카이빙에서 볼 수 있어요</Styled.Description>
          </Styled.DescriptionWrapper>
        </Styled.TitleWrapper>
        <Styled.Image src={`${imageUrl}001.png`} />
      </Styled.Box>
    </Styled.Container>
  );
}
