import { fetcher } from '@/utils/fetcher';
import { apiPath, cacheKey } from '@/constants';
import { ArchivingCarDataProps } from '@/types/archiving';

import { useFetchSuspense } from '@/hooks/useFetchSuspense';

import * as Styled from './style';

interface Props {
  selectedOptions: Set<string>;
  onSelectOption: (option: string) => void;
}
export function OptionSearchBar({ onSelectOption, selectedOptions }: Props) {
  const { selectOptions } = useFetchSuspense<ArchivingCarDataProps>({
    fetcher: () => fetcher<ArchivingCarDataProps>({ url: apiPath.archivingOption(1) }),
    key: cacheKey.archivingOption(1),
  });

  const options = selectOptions.slice(0, selectOptions.length - 1);
  return (
    <Styled.Conatiner>
      <Styled.Wrapper>
        {options.map(option => {
          const isActive = selectedOptions.has(option.id);
          return (
            <Styled.Option key={option.id} isActive={isActive} onClick={() => onSelectOption(option.id)}>
              {option.name}
            </Styled.Option>
          );
        })}
      </Styled.Wrapper>
    </Styled.Conatiner>
  );
}
