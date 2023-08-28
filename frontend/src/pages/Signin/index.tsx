import { useState, useEffect, useContext, ChangeEvent, FormEvent } from 'react';

import { useNavigate } from 'react-router-dom';

import { setLocalStorage } from '@/utils';
import { isEmailEmpty, isPasswordEmpty, isEmailValid } from '@/utils/auth';
import { CommonError } from '@/utils/CommonError';
import { API_URL, emailRegex, AUTH_ALERT_MESSAGE } from '@/constants';

import { AuthContext } from '@/AuthProvider';

import HyundaiLogo from '/public/assets/icons/signin_hyundai_logo.svg';
import * as Styled from './style';

export function Signin() {
  const [account, setAccount] = useState({
    email: '',
    password: '',
  });
  const [isShow, setIsShow] = useState(false);
  const [alert, setAlert] = useState('');
  const [isDisabled, setIsDisabled] = useState(false);

  const navigate = useNavigate();

  const { isSignin, setIsSignin, setUserName } = useContext(AuthContext);

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

    handleAlert('');

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
        throw new CommonError(message, statusCode);
      }

      const { accessToken, refreshToken, userName } = data;

      setLocalStorage('accessToken', accessToken);
      setLocalStorage('refreshToken', refreshToken);

      setIsSignin(true);
      setUserName(userName);
    } catch (error) {
      if (error instanceof CommonError) {
        const { statusCode } = error;

        if (statusCode === 401) {
          handleAlert(AUTH_ALERT_MESSAGE.ACCOUNT_INCORRECT);
        }
      } else {
        throw error;
      }
    }

    setIsDisabled(false);
  }

  useEffect(() => {
    if (isSignin) {
      navigate('/trim');
    }
  }, [isSignin]);

  return (
    <>
      <Styled.Container>
        <img src={HyundaiLogo} />
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
          <Styled.Flex>
            <Styled.LinkWrapper to="/signup">아직 회원가입을 안하셨나요?</Styled.LinkWrapper>
          </Styled.Flex>
        </Styled.Form>
      </Styled.Container>
    </>
  );
}
