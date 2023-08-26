import { Link } from 'react-router-dom';

import { EmptyContent } from '@/components/common/EmptyContent';

import * as Styled from './style';

interface Props {
  infoText: string;
  buttonText: string;
  toPath: string;
}

export function NoDataInfo({ infoText, buttonText, toPath }: Props) {
  return (
    <Styled.Container>
      <EmptyContent text={infoText} />
      <Link to={toPath}>
        <Styled.Button>{buttonText}</Styled.Button>
      </Link>
    </Styled.Container>
  );
}
