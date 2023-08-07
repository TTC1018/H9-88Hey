import { useState } from 'react';

export function useSelectIndex() {
  const [index, setIndex] = useState(0);

  function handleSetIndex(index: number) {
    return () => setIndex(index);
  }

  return [index, handleSetIndex] as const;
}
