import { useState, useContext, ChangeEvent, FormEvent } from 'react';

import { useNavigate } from 'react-router-dom';

import { AuthContext } from '@/AuthProvider';
import { API_URL } from '@/constants';
import { getLocalStorage, setLocalStorage } from '@/utils';

import * as Styled from './style';

const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

export function Signin() {
  const [account, setAccount] = useState({
    email: '',
    password: '',
  });
  const [isShow, setIsShow] = useState(false);
  const [alert, setAlert] = useState('alert');
  const [isDisabled, setIsDisabled] = useState(false);

  const navigate = useNavigate();

  const { isSignin, setIsSignin, username, setUsername, accessToken, setAccessToken } = useContext(AuthContext);

  function handleChange(event: ChangeEvent<HTMLInputElement>) {
    const { name, value } = event.target;
    setAccount(prev => ({ ...prev, [name]: value }));
  }

  function isEmailEmpty() {
    return account.email === '';
  }

  function isPasswordEmpty() {
    return account.password === '';
  }

  function isEmailValid() {
    return emailRegex.test(account.email);
  }

  function handleAlert(message: string) {
    setIsShow(true);
    setAlert(message);
  }

  async function handleSignin(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();

    setIsDisabled(true);

    if (isEmailEmpty()) {
      handleAlert('이메일 주소가 필요합니다.');
      return;
    }

    if (isPasswordEmpty()) {
      handleAlert('비밀번호가 필요합니다.');
      return;
    }

    if (!isEmailValid()) {
      handleAlert('이메일 주소가 유효하지 않습니다.');
      return;
    }

    try {
      const response = await fetch(`${API_URL}/signin`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(account),
      });
      const { accessToken, refreshToken, username } = await response.json();
      setAccessToken(accessToken);
      setLocalStorage('refreshToken', refreshToken);
      setUsername(username);
    } catch (error: unknown) {
      handleAlert('이메일 또는 비밀번호가 잘못 입력되었습니다.');
    }

    setIsDisabled(false);
  }

  return (
    <>
      <Styled.Container>
        <Styled.HyundaiLogo src="/src/assets/icons/signin_hyundai_logo.svg" />
        <Styled.Form onSubmit={event => handleSignin(event)}>
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
