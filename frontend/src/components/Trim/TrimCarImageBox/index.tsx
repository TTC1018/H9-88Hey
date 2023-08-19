import { TrimDataProps } from '@/types/trim';
import { useFetch } from '@/hooks/useFetch';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { MyCarImageBox } from '@/components/Trim/MyCarImageBox';

const imageInitialData = {
  carImageUrls: [''],
};
export function TrimCarImageBox() {
  // const { carImageUrls } = useFetchSuspense<Pick<TrimDataProps, 'carImageUrls'>>({
  //   url: '/car/model/1/image',
  //   key: ['images'],
  //   staleTime: 3000,
  // });
  const { data: images } = useFetch<Pick<TrimDataProps, 'carImageUrls'>>({
    defaultValue: imageInitialData,
    url: '/car/model/1/image',
  });

  const [selectedIndex, handleSetIndex] = useSelectIndex();

  return (
    <MyCarImageBox
      hasOption={true}
      images={images.carImageUrls}
      selectedIndex={selectedIndex}
      onClick={handleSetIndex}
    />
  );
}
