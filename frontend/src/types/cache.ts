export interface DataProps {
  status: 'new' | 'resolved' | 'rejected';
  dataUpdatedAt: Date;
  data: any;
}

export interface CacheContextProps {
  cachedDataList: Map<string, DataProps>;
}
