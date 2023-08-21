import { useState, Dispatch, SetStateAction, PropsWithChildren, createContext } from 'react';

interface Props {
  isSignin: boolean;
  setIsSignin: Dispatch<SetStateAction<boolean>>;
  userName: string;
  setUserName: Dispatch<SetStateAction<string>>;
  accessToken: string;
  setAccessToken: Dispatch<SetStateAction<string>>;
}

const initialContext = {
  isSignin: false,
  setIsSignin: () => {},
  userName: '',
  setUserName: () => {},
  accessToken: '',
  setAccessToken: () => {},
};

export const AuthContext = createContext<Props>(initialContext);

export function AuthProvider({ children }: PropsWithChildren) {
  const [isSignin, setIsSignin] = useState(false);
  const [userName, setUserName] = useState('');
  const [accessToken, setAccessToken] = useState('');

  return (
    <AuthContext.Provider value={{ isSignin, setIsSignin, userName, setUserName, accessToken, setAccessToken }}>
      {children}
    </AuthContext.Provider>
  );
}
