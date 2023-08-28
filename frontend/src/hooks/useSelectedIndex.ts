import { useState } from 'react';

interface Props {
  initialIndex: number;
}
export function useSelectIndex({ initialIndex }: Props) {
  const [index, setIndex] = useState(initialIndex);

  function handleSetIndex(index: number) {
    return () => setIndex(index);
  }

  return [index, handleSetIndex] as const;
}
