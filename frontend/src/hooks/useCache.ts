import { useContext } from 'react';
import { CacheContext } from '@/CacheProvider';
import { DataProps } from '@/types/cache';

export function useCache() {
  const { cachedDataList } = useContext(CacheContext);

  function getCache({ key }: { key: string[] }) {
    const stringKey = JSON.stringify(key);

    return cachedDataList.get(stringKey);
  }

  function setCache({ key, value }: { key: string[]; value: DataProps }) {
    const stringKey = JSON.stringify(key);

    cachedDataList.set(stringKey, value);
  }

  return { getCache, setCache };
}
