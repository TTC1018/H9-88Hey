import { Dispatch, createContext, useContext } from 'react';

export interface MyCarContextType {
  index: number;
  setIndex: Dispatch<React.SetStateAction<number>>;
}

export const NavIndexContext = createContext<MyCarContextType | null>(null);

export function useMyCarContext(): MyCarContextType {
  const context = useContext(NavIndexContext);
  if (context === null) {
    throw new Error('useMyCarContext must be used within a MyProvider');
  }
  return context;
}
