import { useState, ChangeEvent, MouseEvent } from 'react';
import { useNavigate } from 'react-router-dom';

import * as Styled from './style';

interface User {
  id: string;
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

  function handleLogout() {
    setUser(null);
    localStorage.removeItem('accessToken');
    localStorage.removeItem('refreshToken');
  }

  return (
    <>
      {user !== null ? (
        navigate('/trim')
      ) : (
        <Styled.Container>
          <Styled.Form>
            <input type="text" name="id" placeholder="아이디" onChange={handleChange} />
            <input type="password" name="password" placeholder="비밀번호" onChange={handleChange} />
            <button onClick={event => handleLogin(event)}>로그인</button>
            <button onClick={() => handleLogout()}>로그아웃</button>
            <button onClick={() => setUser({ id: 'sukam09', username: '이승원' })}>setUser Test Button</button>
          </Styled.Form>
        </Styled.Container>
      )}
    </>
  );
}
