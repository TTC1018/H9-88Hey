export interface DataProps {
  status: 'new' | 'resolved' | 'rejected';
  dataUpdatedAt: Date;
  data: any;
}

export interface CacheContextProps {
  cachedDatas: Map<string, DataProps>;
}
