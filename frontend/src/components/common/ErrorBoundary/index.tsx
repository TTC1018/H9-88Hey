import { Component, ComponentType, ReactNode } from 'react';

import * as Styled from './style';

type ErrorHandlingComponent<Props> = (props: Props, error?: Error) => ReactNode;

type ErrorState = { error?: Error };

function Catch<Props extends {}>(component: ErrorHandlingComponent<Props>): ComponentType<Props> {
  return class extends Component<Props, ErrorState> {
    state: ErrorState = {
      error: undefined,
    };

    static getDerivedStateFromError(error: Error) {
      return { error };
    }

    render() {
      return component(this.props, this.state.error);
    }
  };
}

type Props = {
  children: ReactNode;
};

export const ErrorBoundary = Catch(function ErrorBoundary(props: Props, error?: Error) {
  // const navigate = useNavigate();
  if (error) {
    return (
      <Styled.Container>
        <Styled.Wrapper>
          <Styled.Image src={'https://www.hyundai.com/static/images/logo.png'} />
          <Styled.Head>현대닷컴 접속이 원활하지 않습니다.</Styled.Head>
          <Styled.Body> 일시적인 현상이거나, 네트워크 문제일 수 있으니</Styled.Body>
          <Styled.Body>잠시 후 다시 시도해주세요.</Styled.Body>
          <Styled.Body>트림선택후에 시도해주세요.</Styled.Body>
          <Styled.Button href="/trim">트림 선택하러 가기</Styled.Button>
          <h4>{error.message}</h4>
        </Styled.Wrapper>
      </Styled.Container>
    );
  } else {
    return <>{props.children}</>;
  }
});
