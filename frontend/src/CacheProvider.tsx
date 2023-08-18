import { PropsWithChildren, createContext } from 'react';

import { CacheContextProps } from './types/cache';

export const CacheContext = createContext<CacheContextProps>({
  cachedDataList: new Map(),
});

export function CacheProvider({ children }: PropsWithChildren) {
  const cachedDataList = new Map();

  return <CacheContext.Provider value={{ cachedDataList }}>{children}</CacheContext.Provider>;
}
