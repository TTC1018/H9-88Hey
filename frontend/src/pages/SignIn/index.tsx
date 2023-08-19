import { useState, ChangeEvent, MouseEvent } from 'react';

import { useNavigate } from 'react-router-dom';

import * as Styled from './style';

interface User {
  username: string;
}

interface AuthResponse {
  accessToken: string;
  refreshToken: string;
  user: User;
}

export function SignIn() {
  const [account, setAccount] = useState({
    id: '',
    password: '',
  });
  const [user, setUser] = useState<User | null>(null);

  const navigate = useNavigate();

  function handleChange(event: ChangeEvent<HTMLInputElement>) {
    const { name, value } = event.target;
    setAccount(prev => ({ ...prev, [name]: value }));
  }

  async function handleLogin(event: MouseEvent<HTMLButtonElement>) {
    event.preventDefault();
    try {
      const response = await fetch('/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(account),
      });
      const { user, accessToken, refreshToken }: AuthResponse = await response.json();
      setUser(user);
      localStorage.setItem('accessToken', accessToken);
      localStorage.setItem('refreshToken', refreshToken);
      navigate('/trim');
    } catch (error: unknown) {
      console.error(`Login failed: ${error}`);
    }
  }

  return (
    <>
      {user !== null ? (
        navigate('/trim')
      ) : (
        <Styled.Container>
          <Styled.HyundaiLogo src="/src/assets/icons/signin_hyundai_logo.svg" />
          <Styled.Form>
            <Styled.Input type="text" name="id" placeholder="이메일 주소" onChange={handleChange} />
            <Styled.Input type="password" name="password" placeholder="비밀번호" onChange={handleChange} />
            <Styled.Button onClick={event => handleLogin(event)}>로그인</Styled.Button>
          </Styled.Form>
        </Styled.Container>
      )}
    </>
  );
}
