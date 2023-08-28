import { SelectOptionsProps } from '@/types/archiving';

import * as Styled from './style';

interface Props {
  props: SelectOptionsProps;
}
export function OptionDescriptionCard({ props }: Props) {
  const { name, imageUrl, subOptions, tags, review } = props;

  return (
    <Styled.Container>
      <Styled.Image src={imageUrl} />
      <Styled.Text>{name}</Styled.Text>
      <Styled.Line />
      <Styled.SubOptions>{subOptions.join(' | ')}</Styled.SubOptions>
      <Styled.Review>{review}</Styled.Review>
      <Styled.TagWrapper>
        {tags.map(tag => (
          <Styled.Tag key={tag}>{tag}</Styled.Tag>
        ))}
      </Styled.TagWrapper>
    </Styled.Container>
  );
}
