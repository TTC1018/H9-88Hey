import { useState, useEffect, useContext, ChangeEvent, FormEvent } from 'react';

import { useNavigate } from 'react-router-dom';

import { AuthContext } from '@/AuthProvider';
import { getLocalStorage, setLocalStorage } from '@/utils';
import { isEmailEmpty, isPasswordEmpty, isEmailValid } from '@/utils/auth';
import { API_URL, emailRegex, AUTH_ALERT_MESSAGE } from '@/constants';

import * as Styled from './style';

class AuthError extends Error {
  statusCode: number;

  constructor(message: string, statusCode: number) {
    super(message);
    this.statusCode = statusCode;
  }
}

export function Signin() {
  const [account, setAccount] = useState({
    email: '',
    password: '',
  });
  const [isShow, setIsShow] = useState(false);
  const [alert, setAlert] = useState('alert');
  const [isDisabled, setIsDisabled] = useState(false);

  const navigate = useNavigate();

  const { isSignin, setIsSignin, setUserName, setAccessToken } = useContext(AuthContext);

  function handleChange(event: ChangeEvent<HTMLInputElement>) {
    const { name, value } = event.target;
    setAccount(prev => ({ ...prev, [name]: value }));
  }

  function handleAlert(message: string) {
    setIsShow(true);
    setAlert(message);
    setIsDisabled(false);
  }

  async function handleSignin(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    setIsDisabled(true);
    const { email, password } = account;

    if (isSignin) {
      navigate('/trim', { replace: true });
    }

    if (isEmailEmpty(email)) {
      handleAlert(AUTH_ALERT_MESSAGE.EMAIL_EMPTY);
      return;
    }

    if (isPasswordEmpty(password)) {
      handleAlert(AUTH_ALERT_MESSAGE.PASSWORD_EMPTY);
      return;
    }

    if (!isEmailValid(email, emailRegex)) {
      handleAlert(AUTH_ALERT_MESSAGE.EMAIL_INVALID);
      return;
    }

    try {
      const response = await fetch(`${API_URL}/auth/signin`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(account),
      });

      const { statusCode, message, data } = await response.json();

      if (!response.ok) {
        throw new AuthError(message, statusCode);
      }

      const { accessToken, refreshToken, userName } = data;

      setAccessToken(accessToken);
      setLocalStorage('refreshToken', refreshToken);

      setIsSignin(true);
      setUserName(userName);
    } catch (error: unknown) {
      if (error instanceof AuthError) {
        const { statusCode } = error;

        if (statusCode === 401) {
          handleAlert(AUTH_ALERT_MESSAGE.ACCOUNT_INCORRECT);
        }
      }
    }

    setIsDisabled(false);
  }

  useEffect(() => {
    const refreshToken = getLocalStorage('refreshToken');

    if (refreshToken !== null) {
      setIsSignin(true);
    }
  }, []);

  useEffect(() => {
    if (isSignin) {
      navigate('/trim', { replace: true });
    }
  }, [isSignin]);

  return (
    <>
      <Styled.Container>
        <Styled.HyundaiLogo src="/src/assets/icons/signin_hyundai_logo.svg" />
        <Styled.Form onSubmit={handleSignin}>
          <Styled.Alert isShow={isShow}>{alert}</Styled.Alert>
          <Styled.Input
            type="text"
            name="email"
            placeholder="이메일 주소"
            onChange={handleChange}
            disabled={isDisabled}
          />
          <Styled.Input
            type="password"
            name="password"
            placeholder="비밀번호"
            onChange={handleChange}
            disabled={isDisabled}
          />
          <Styled.Button type="submit" disabled={isDisabled}>
            로그인
          </Styled.Button>
        </Styled.Form>
      </Styled.Container>
    </>
  );
}
