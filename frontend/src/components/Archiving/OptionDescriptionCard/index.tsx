import { useState } from 'react';

import { SelectOptionsProps } from '@/types/archiving';

import * as Styled from './style';

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
        <Styled.SubOptions>{subOptions.join(' | ')}</Styled.SubOptions>
        <Styled.Review>{review}</Styled.Review>
      </Styled.ContentWrapper>
      <Styled.TagWrapper>
        {tags.map(tag => (
          <Styled.Tag key={tag}>{tag}</Styled.Tag>
        ))}
      </Styled.TagWrapper>
      <Styled.Button onClick={() => setIsActive(prev => !prev)}> 접기 </Styled.Button>
    </Styled.Container>
  );
}
