import { Dispatch, createContext, useContext } from 'react';

export interface MyContextType {
  index: number;
  setIndex: Dispatch<React.SetStateAction<number>>;
}

export const NavIndexContext = createContext<MyContextType | null>(null);

export function useMyContext(): MyContextType {
  const context = useContext(NavIndexContext);
  if (context === null) {
    throw new Error('useMyContext must be used within a MyProvider');
  }
  return context;
}
