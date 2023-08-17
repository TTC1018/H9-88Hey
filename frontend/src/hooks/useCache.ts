import { useContext } from 'react';
import { CacheContext } from '@/CacheProvider';
import { DataProps } from '@/types/cache';

export function useCache() {
  const { cachedDatas } = useContext(CacheContext);

  function getCache({ key }: { key: string[] }) {
    const stringKey = JSON.stringify(key);

    return cachedDatas.get(stringKey);
  }

  function setCache({ key, value }: { key: string[]; value: DataProps }) {
    const stringKey = JSON.stringify(key);

    cachedDatas.set(stringKey, value);
  }

  return { getCache, setCache };
}
