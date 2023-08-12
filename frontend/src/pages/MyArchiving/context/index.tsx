import { Dispatch, createContext, useContext } from 'react';

export interface MyContextType {
  index: number;
  setIndex: Dispatch<React.SetStateAction<number>>;
}

export const myContext = createContext<MyContextType | null>(null);

export function useMyContext(): MyContextType {
  const context = useContext(myContext);
  if (context === null) {
    throw new Error('useMyContext must be used within a MyProvider');
  }
  return context;
}
