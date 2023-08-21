import { MyFeedDataProps } from '@/types/myChiving';
import { useFetch } from '@/hooks/useFetch';

import { FeedList } from '@/components/MyChiving/FeedList';
import { NoDataInfo } from '@/components/MyChiving/NoDataInfo';

import * as Styled from './style';

export function MyFeed() {
  const {
    data: { archivingsByUser },
  } = useFetch<MyFeedDataProps>({ defaultValue: {} as MyFeedDataProps, url: '/mychiving/feed' });

  return (
    <Styled.Container>
      {archivingsByUser && archivingsByUser.length > 0 ? (
        <FeedList myChivings={archivingsByUser} />
      ) : (
        <NoDataInfo
          infoText="피드에 저장한 차량이 없어요"
          buttonText="아카이빙에서 차량 검색하기"
          toPath="/archiving"
        />
      )}
    </Styled.Container>
  );
}
