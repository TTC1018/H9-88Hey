import { PropsWithChildren, createContext } from 'react';

import { cacheContextProps } from './types/cache';

export const CacheContext = createContext<cacheContextProps>({
  cachedDatas: new Map(),
});

export function CacheProvider({ children }: PropsWithChildren) {
  const cachedDatas = new Map();

  return <CacheContext.Provider value={{ cachedDatas }}>{children}</CacheContext.Provider>;
}
