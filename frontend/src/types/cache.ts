export interface dataProps {
  dataUpdatedAt: Date;
  data: any;
}

export interface CacheContextProps {
  cachedDatas: Map<string, dataProps>;
}
