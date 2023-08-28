import { EmptyLogo } from '@/components/common/EmptyLogo';

import * as Styled from './style';

interface Props {
  text: string;
  isNotFound?: boolean;
}
export function EmptyContent({ text, isNotFound = false }: Props) {
  return (
    <Styled.Container>
      {isNotFound && <Styled.Head>404 Not Found</Styled.Head>}
      <EmptyLogo />
      <Styled.Text>{text}</Styled.Text>
    </Styled.Container>
  );
}
