import { EmptyLogo } from '@/components/common/EmptyLogo';

import * as Styled from './style';

export function EmptyContent() {
  return (
    <Styled.Container>
      <EmptyLogo />
      <Styled.Text>해당 선택옵션에 대한 검색 결과가 없습니다.</Styled.Text>
    </Styled.Container>
  );
}
