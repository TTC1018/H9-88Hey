import { Link } from 'react-router-dom';

import * as Styled from './style';

interface Props {
  infoText: string;
  buttonText: string;
  toPath: string;
}

export function NoDataInfo({ infoText, buttonText, toPath }: Props) {
  return (
    <Styled.Container>
      <Styled.Text>{infoText}</Styled.Text>
      <Link to={toPath}>
        <Styled.Button>{buttonText}</Styled.Button>
      </Link>
    </Styled.Container>
  );
}
