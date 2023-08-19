import { useState, Dispatch, SetStateAction, PropsWithChildren, createContext } from 'react';

interface Props {
  isSignin: boolean;
  setIsSignin: Dispatch<SetStateAction<boolean>>;
  username: string;
  setUsername: Dispatch<SetStateAction<string>>;
  accessToken: string;
  setAccessToken: Dispatch<SetStateAction<string>>;
}

export const AuthContext = createContext<Props | null>(null);

export function AuthProvider({ children }: PropsWithChildren) {
  const [isSignin, setIsSignin] = useState(false);
  const [username, setUsername] = useState('');
  const [accessToken, setAccessToken] = useState('');

  return (
    <AuthContext.Provider value={{ isSignin, setIsSignin, username, setUsername, accessToken, setAccessToken }}>
      {children}
    </AuthContext.Provider>
  );
}
