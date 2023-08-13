import { useFetch } from '@/hooks/useFetch';
import { FeedMyChivingDataProps } from '@/types/myChiving';

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
      creatrionDate: '',
      selectedOptions: [
        {
          name: '',
          imageUrl: '',
        },
      ],
      review: '',
      tags: [''],
    },
  ],
};

export function MyFeed() {
  const { data } = useFetch<FeedMyChivingDataProps>({ defaultValue: initialData, url: '/mychiving/feed' });

  return (
    <Styled.Container>
      {data.archivingsByUser.length > 0 ? (
        <>FEED</>
      ) : (
        <Styled.NoDataInfoBox>
          <Styled.NoDataInfoText>피드에 저장한 차량이 없어요</Styled.NoDataInfoText>
          <Styled.CreateMyCarButton>아카이빙에서 차량 검색하기</Styled.CreateMyCarButton>
        </Styled.NoDataInfoBox>
      )}
    </Styled.Container>
  );
}
