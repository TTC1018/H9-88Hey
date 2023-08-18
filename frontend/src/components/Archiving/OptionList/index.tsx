import { useEffect, useRef } from 'react';

import { masonryLayout } from '@/utils/masonryLayout';
import { SelectOptionsProps } from '@/types/archiving';

import { OptionDescriptionCard } from '@/components/Archiving/OptionDescriptionCard';

import * as Styled from './style';

interface Props {
  options: SelectOptionsProps[];
}
export function OptionList({ options }: Props) {
  const masonryRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    masonryLayout({ element: masonryRef });
  }, [options]);

  return (
    <Styled.Container ref={masonryRef}>
      {options.map(option => (
        <Styled.Wrapper>
          <OptionDescriptionCard key={option.name} props={option} />
        </Styled.Wrapper>
      ))}
    </Styled.Container>
  );
}
