import { fetcher } from '@/utils/fetcher';
import { TagDataProps } from '@/types/trim';
import { apiPath, cacheKey } from '@/constants';

import { useFetchSuspense } from '@/hooks/useFetchSuspense';

import * as Styled from './style';

interface Props {
  tagId: number | string;
  type: string;
  limit: number;
}
export function TagBox({ tagId, type, limit }: Props) {
  const { tags } = useFetchSuspense<TagDataProps>({
    fetcher: () =>
      fetcher<TagDataProps>({
        url: apiPath.tag(type, tagId, limit),
      }),
    key: cacheKey.tag(type, tagId),
  });

  return (
    <Styled.Container>
      {tags.map(tag => (
        <Styled.Tag key={tag}>{tag}</Styled.Tag>
      ))}
    </Styled.Container>
  );
}
