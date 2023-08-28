import { useState, useEffect, useContext, ChangeEvent, FormEvent } from 'react';

import { useNavigate } from 'react-router-dom';

import { AuthContext } from '@/AuthProvider';
import { setLocalStorage } from '@/utils';
import {
  isEmailEmpty,
  isPasswordEmpty,
  isConfirmPasswordEmpty,
  isUserNameEmpty,
  isEmailValid,
  isPasswordIncorrect,
  isPasswordInvalid,
} from '@/utils/auth';
import { CommonError } from '@/utils/CommonError';
import { API_URL, emailRegex, AUTH_ALERT_MESSAGE } from '@/constants';

import HyundaiLogo from '/public/assets/icons/signin_hyundai_logo.svg';
import * as Styled from './style';

export function Signup() {
  const [account, setAccount] = useState({
    email: '',
    password: '',
  });
  const [confirmPassword, setConfirmPassword] = useState('');
  const [user, setUser] = useState('');

  const [isShow, setIsShow] = useState(false);
  const [alert, setAlert] = useState('');
  const [isDisabled, setIsDisabled] = useState(false);

  const navigate = useNavigate();

  const { isSignin, setIsSignin, setUserName } = useContext(AuthContext);

  function handleChangeAccount(event: ChangeEvent<HTMLInputElement>) {
    const { name, value } = event.target;
    setAccount(prev => ({ ...prev, [name]: value }));
  }

  function handleChangeConfirmPassword(event: ChangeEvent<HTMLInputElement>) {
    setConfirmPassword(event.target.value);
  }

  function handleChangeUserName(event: ChangeEvent<HTMLInputElement>) {
    setUser(event.target.value);
  }

  function handleAlert(message: string) {
    setIsShow(true);
    setAlert(message);
    setIsDisabled(false);
  }

  async function handleSignup(event: FormEvent<HTMLFormElement>) {
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

    if (isConfirmPasswordEmpty(confirmPassword)) {
      handleAlert(AUTH_ALERT_MESSAGE.CONFIRM_PASSWORD_EMPTY);
      return;
    }

    if (isUserNameEmpty(user)) {
      handleAlert(AUTH_ALERT_MESSAGE.USER_NAME_EMPTY);
      return;
    }

    if (!isEmailValid(email, emailRegex)) {
      handleAlert(AUTH_ALERT_MESSAGE.EMAIL_INVALID);
      return;
    }

    if (isPasswordIncorrect(password, confirmPassword)) {
      handleAlert(AUTH_ALERT_MESSAGE.PASSWORD_INCORRECT);
      return;
    }

    if (isPasswordInvalid(password)) {
      handleAlert(AUTH_ALERT_MESSAGE.PASSWORD_INVALID);
      return;
    }

    handleAlert('');

    try {
      const response = await fetch(`${API_URL}/auth/signup`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ ...account, userName: user }),
      });

      const { statusCode, message, data } = await response.json();

      if (!response.ok) {
        throw new CommonError(message, statusCode);
      }

      const { accessToken, refreshToken } = data;

      setLocalStorage('accessToken', accessToken);
      setLocalStorage('refreshToken', refreshToken);

      setIsSignin(true);
      setUserName(user);
    } catch (error) {
      if (error instanceof CommonError) {
        const { statusCode } = error;

        if (statusCode === 409) {
          handleAlert(AUTH_ALERT_MESSAGE.EMAIL_CONFLICT);
        }
      } else {
        throw error;
      }
    }

    setIsDisabled(false);
  }

  useEffect(() => {
    if (isSignin) {
      navigate('/trim', { replace: true });
    }
  }, [isSignin]);

  return (
    <>
      <Styled.Container>
        <img src={HyundaiLogo} />
        <Styled.Form onSubmit={handleSignup}>
          <Styled.Alert isShow={isShow}>{alert}</Styled.Alert>
          <Styled.Input
            type="text"
            name="email"
            placeholder="이메일 주소"
            onChange={handleChangeAccount}
            disabled={isDisabled}
          />
          <Styled.Input
            type="password"
            name="password"
            placeholder="비밀번호"
            onChange={handleChangeAccount}
            disabled={isDisabled}
          />
          <Styled.Input
            type="password"
            placeholder="비밀번호 확인"
            onChange={handleChangeConfirmPassword}
            disabled={isDisabled}
          />
          <Styled.Input type="text" placeholder="이름" onChange={handleChangeUserName} disabled={isDisabled} />
          <Styled.Button type="submit" disabled={isDisabled}>
            회원가입
          </Styled.Button>
          <Styled.LinkWrapper to="/">이미 아이디가 있으신가요?</Styled.LinkWrapper>
        </Styled.Form>
      </Styled.Container>
    </>
  );
}
