import * as style from './style';

interface Props {
  options: string[];
  selectOptions: Set<string>;
  onSelectOption: (option: string) => void;
}
export function OptionSearchBar({ options, selectOptions, onSelectOption }: Props) {
  return (
    <style.Conatiner>
      <style.Wrapper>
        {options.map(option => {
          const isActive = selectOptions.has(option);
          return (
            <style.Option isActive={isActive} onClick={() => onSelectOption(option)}>
              {option}
            </style.Option>
          );
        })}
      </style.Wrapper>
    </style.Conatiner>
  );
}
