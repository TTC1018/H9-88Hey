import { TrimDataProps } from '@/types/trim';
import { useSelectIndex } from '@/hooks/useSelectedIndex';

import { MyCarImageBox } from '@/components/Trim/MyCarImageBox';
import { useFetchSuspense } from '@/hooks/useFetchSuspense';
import { fetcher } from '@/utils/fetcher';
import { apiPath, cacheKey } from '@/constants';

export function TrimCarImageBox() {
  const { carImageUrls } = useFetchSuspense<Pick<TrimDataProps, 'carImageUrls'>>({
    fetcher: () => fetcher<Pick<TrimDataProps, 'carImageUrls'>>({ url: apiPath.image(1) }),
    key: cacheKey.image(1),
  });

  const [selectedIndex, handleSetIndex] = useSelectIndex({ initialIndex: 0 });

  return (
    <MyCarImageBox hasOption={true} images={carImageUrls} selectedIndex={selectedIndex} onClick={handleSetIndex} />
  );
}
