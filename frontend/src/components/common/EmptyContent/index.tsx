import { EmptyLogo } from '@/components/common/EmptyLogo';

import * as Styled from './style';

interface Props {
  text: string;
}
export function EmptyContent({ text }: Props) {
  return (
    <Styled.Container>
      <EmptyLogo />
      <Styled.Text>{text}</Styled.Text>
    </Styled.Container>
  );
}
