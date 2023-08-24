import { useRef } from 'react';

import { ArchivingProps } from '@/types/archiving';

import { FeedList } from '@/components/MyChiving/FeedList';
import { NoDataInfo } from '@/components/MyChiving/NoDataInfo';
import { useInfiniteFetch } from '@/hooks/useInfiniteFetch';
import { useInfiniteScroll } from '@/hooks/useInfiniteScroll';
import { ReviewSkeleton } from '@/components/common/ReviewSkeleton';

import * as Styled from './style';

export function MyFeed() {
  const fetchMoreElement = useRef<HTMLDivElement>(null);
  const intersecting = useInfiniteScroll(fetchMoreElement);
  const nextOffset = useRef(1);

  const { data: archivingsByUser, isLoading } = useInfiniteFetch<ArchivingProps>({
    key: 'archivingsByUser',
    url: `/user/archiving/bookmark?limit=6&offset=${nextOffset.current}`,
    intersecting,
    nextOffset,
    dependencies: [''],
  });

  return (
    <Styled.Container>
      {archivingsByUser && archivingsByUser.length === 0 ? (
        isLoading ? (
          <ReviewSkeleton />
        ) : (
          <NoDataInfo
            infoText="피드에 저장한 차량이 없어요"
            buttonText="아카이빙에서 차량 검색하기"
            toPath="/archiving"
          />
        )
      ) : (
        <>
          <FeedList myFeedChiving={archivingsByUser} />
        </>
      )}
      <div ref={fetchMoreElement}></div>
    </Styled.Container>
  );
}
