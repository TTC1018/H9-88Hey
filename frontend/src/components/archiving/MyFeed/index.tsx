import { useFetch } from '@/hooks/useFetch';
import { FeedMyChivingDataProps } from '@/types/myChiving';

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
  return <>My Feed</>;
}
