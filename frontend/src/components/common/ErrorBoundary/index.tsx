import { Component, ComponentType, ReactNode } from 'react';

import * as style from './style';

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

export const ErrorBoundary = Catch(function MyErrorBoundary(props: Props, error?: Error) {
  if (error) {
    return (
      <style.Container>
        <style.Wrapper>
          <style.Image src={'https://www.hyundai.com/static/images/logo.png'} />
          <style.Head>현대닷컴 접속이 원활하지 않습니다.</style.Head>
          <style.Body> 일시적인 현상이거나, 네트워크 문제일 수 있으니</style.Body>
          <style.Body>잠시 후 다시 시도해주세요.</style.Body>
          <h4>{error.message}</h4>
        </style.Wrapper>
      </style.Container>
    );
  } else {
    return <>{props.children}</>;
  }
});
