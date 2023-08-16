import { PropsWithChildren, createContext } from 'react';

import { CacheContextProps } from './types/cache';

export const CacheContext = createContext<CacheContextProps>({
  cachedDatas: new Map(),
});

export function CacheProvider({ children }: PropsWithChildren) {
  const cachedDatas = new Map();

  return <CacheContext.Provider value={{ cachedDatas }}>{children}</CacheContext.Provider>;
}
