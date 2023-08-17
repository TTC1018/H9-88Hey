import { useEffect, useRef } from 'react';

import { SelectOptionsProps } from '@/types/archiving';
import { useMasonryLayout } from '@/hooks/useMasonryLayout';

import { OptionDescriptionCard } from '@/components/Archiving/OptionDescriptionCard';

import * as Styled from './style';

interface Props {
  options: SelectOptionsProps[];
}
export function OptionList({ options }: Props) {
  const masonryRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    useMasonryLayout({ element: masonryRef });
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
