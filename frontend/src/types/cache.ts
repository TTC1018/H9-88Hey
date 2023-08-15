export interface dataProps {
  dataUpdatedAt: Date;
  data: any;
}

export interface cacheContextProps {
  cachedDatas: Map<string, dataProps>;
}
