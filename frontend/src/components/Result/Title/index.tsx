import { hasJongSeong } from '@/utils';

import * as Styled from './style';

interface Props {
  krName: string;
  enName: string;
}

export function Title({ krName, enName }: Props) {
  return (
    <Styled.Container>
      <Styled.TitleWrapper>
        <Styled.Title>{enName.toUpperCase()}</Styled.Title>
        <Styled.TitleLine />
        <Styled.Message>
          나의 {krName}
          {hasJongSeong(krName) ? '이' : '가'} 완성되었어요!
        </Styled.Message>
        <Styled.DescriptionWrapper>
          <Styled.Description>완성된 차량은 마이페이지</Styled.Description>
          <Styled.RightArrow src="/src/assets/icons/arrow_right.svg" />
          <Styled.Description>마이카이빙에서 볼 수 있어요</Styled.Description>
        </Styled.DescriptionWrapper>
      </Styled.TitleWrapper>
      <Styled.Image src="https://www.hyundai.com/contents/vr360/LX06/exterior/WC9/001.png" />
    </Styled.Container>
  );
}
