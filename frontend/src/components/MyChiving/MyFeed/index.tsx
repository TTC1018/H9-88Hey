import { MyFeedDataProps } from '@/types/myChiving';
import { useFetch } from '@/hooks/useFetch';

import { FeedList } from '@/components/MyChiving/FeedList';

import * as Styled from './style';

const initialData = {
  archivingsByUser: [
    {
      id: 1,
      model: '',
      trim: '',
      isPurchase: false,
      trimOptions: [''],
      interiorColor: '',
      exteriorColor: '',
      creationDate: '',
      selectedOptions: [
        {
          name: '',
          imageUrl: '',
          subOptions: [''],
          tags: [''],
          review: '',
        },
      ],
      review: '',
      tags: [''],
      totalPrice: 0,
    },
  ],
};

export function MyFeed() {
  const {
    data: { archivingsByUser },
  } = useFetch<MyFeedDataProps>({ defaultValue: initialData, url: '/mychiving/feed' });

  return (
    <Styled.Container>
      {archivingsByUser.length > 0 ? (
        <FeedList myChivings={archivingsByUser} />
      ) : (
        <Styled.NoDataInfoBox>
          <Styled.NoDataInfoText>피드에 저장한 차량이 없어요</Styled.NoDataInfoText>
          <Styled.CreateMyCarButton>아카이빙에서 차량 검색하기</Styled.CreateMyCarButton>
        </Styled.NoDataInfoBox>
      )}
    </Styled.Container>
  );
}
