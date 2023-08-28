import { EmptyContent } from '@/components/common/EmptyContent';

import * as Styled from './style';

export function NotFound() {
  return (
    <Styled.Container>
      <EmptyContent text="요청하신 페이지를 찾을 수 없어요" isNotFound />
    </Styled.Container>
  );
}
