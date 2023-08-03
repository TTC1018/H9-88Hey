import { useState } from 'react';

export function useSelectIndex() {
  const [index, setIndex] = useState(0);

  const handleClick = (index: number) => () => {
    setIndex(index);
  };

  return [index, handleClick] as const;
}
