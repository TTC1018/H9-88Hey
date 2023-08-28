import { Component, ComponentType, ReactNode } from 'react';

import { CommonError } from '@/utils/CommonError';

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

export const ErrorBoundary = Catch(function ErrorBoundary(props: Props, error?: Error | CommonError) {
  if (error !== undefined) {
    return (
      <Styled.Container>
        <Styled.Wrapper>
          <Styled.Image src={'https://www.hyundai.com/static/images/logo.png'} />
          {error instanceof CommonError && (error.statusCode === 400 || error.statusCode === 401) ? (
            <>
              <Styled.Head>로그인 상태가 아니거나 세션이 만료되었습니다.</Styled.Head>
              <Styled.Body>로그인 후 다시 시도해주세요.</Styled.Body>
              <Styled.Button href="/">로그인 페이지로 이동</Styled.Button>
            </>
          ) : (
            <>
              <Styled.Head>현대닷컴 접속이 원활하지 않습니다.</Styled.Head>
              <Styled.Body>일시적인 현상이거나, 네트워크 문제일 수 있으니</Styled.Body>
              <Styled.Body>잠시 후 다시 시도해주세요.</Styled.Body>
              <Styled.Button href="/trim">메인 페이지로 이동</Styled.Button>
            </>
          )}
        </Styled.Wrapper>
      </Styled.Container>
    );
  }

  return <>{props.children}</>;
});
