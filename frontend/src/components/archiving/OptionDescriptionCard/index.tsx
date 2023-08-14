import { SelectOptionsProps } from '@/types/archiving';

import * as Styled from './style';
import { useState } from 'react';

interface Props {
  props: SelectOptionsProps;
}
export function OptionDescriptionCard({ props }: Props) {
  const { name, imageUrl, subOptions, tags, review } = props;
  const [isActive, setIsActive] = useState(false);
  return (
    <Styled.Container>
      <Styled.Image src={imageUrl} />
      <Styled.Text>{name}</Styled.Text>
      <Styled.Line />
      <Styled.ContentWrapper isActive={isActive}>
        <Styled.SubOptions>
          {[
            '후석 승객 알림',
            '후석 승객 알림',
            '후석 승객 알림',
            '후석 승객 알림',
            '후석 승객 알림',
            '후석 승객 알림',
          ].join(' | ')}
        </Styled.SubOptions>
        <Styled.Review>
          승차감이 좋아요 차가 크고 운전하는 시야도 높아서 좋았어요 저는 13개월 아들이 있는데 뒤에 차시트 달아도 널널할
          것 같습니다.
        </Styled.Review>
      </Styled.ContentWrapper>
      <Styled.TagWrapper>
        {tags.map(tag => (
          <Styled.Tag key={tag}>{tag}</Styled.Tag>
        ))}
      </Styled.TagWrapper>
      <Styled.Button onClick={() => setIsActive(!isActive)}> 접기 </Styled.Button>
    </Styled.Container>
  );
}
