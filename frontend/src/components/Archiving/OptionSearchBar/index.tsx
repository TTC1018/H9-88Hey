import { useFetchSuspense } from '@/hooks/useFetchSuspense';
import * as style from './style';
import { ArchivingCarDataProps } from '@/types/archiving';
import { fetcher } from '@/utils/fetcher';
import { apiPath, cacheKey } from '@/constants';

interface Props {
  selectedOptions: Set<string>;
  onSelectOption: (option: string) => void;
}
export function OptionSearchBar({ onSelectOption, selectedOptions }: Props) {
  const { selectOptions } = useFetchSuspense<ArchivingCarDataProps>({
    fetcher: () => fetcher<ArchivingCarDataProps>({ url: apiPath.archivingOption(1) }),
    key: cacheKey.archivingOption(1),
  });

  const options = selectOptions.slice(0, selectOptions.length - 4);
  return (
    <style.Conatiner>
      <style.Wrapper>
        {options.map(option => {
          const isActive = selectedOptions.has(option.id);
          return (
            <style.Option key={option.id} isActive={isActive} onClick={() => onSelectOption(option.id)}>
              {option.name}
            </style.Option>
          );
        })}
      </style.Wrapper>
    </style.Conatiner>
  );
}
