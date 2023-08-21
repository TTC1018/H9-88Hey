import { useLayoutEffect, useRef } from 'react';

import { updateMasonryLayout } from '@/utils/updateMasonryLayout';
import { SelectOptionsProps } from '@/types/archiving';

import { OptionDescriptionCard } from '@/components/Archiving/OptionDescriptionCard';

import * as Styled from './style';

interface Props {
  options: SelectOptionsProps[];
}
export function OptionList({ options }: Props) {
  const masonryRef = useRef<HTMLDivElement>(null);

  useLayoutEffect(() => {
    updateMasonryLayout({ element: masonryRef });
  }, [options]);

  return (
    <Styled.Container ref={masonryRef}>
      {options.map(option => (
        <Styled.Wrapper key={option.id}>
          <OptionDescriptionCard props={option} />
        </Styled.Wrapper>
      ))}
    </Styled.Container>
  );
}
