import { RefObject } from 'react';

interface Props {
  element: RefObject<HTMLElement> | null;
}

export function masonryLayout({ element }: Props) {
  if (element === null) {
    return;
  }

  const masonryContainer = element.current;

  if (masonryContainer === null) {
    return;
  }

  const masonryItems = masonryContainer.childNodes;
  const elementGap = parseInt(getComputedStyle(masonryContainer).gap);
  const elementAutoRow = parseInt(getComputedStyle(masonryContainer).gridAutoRows);

  masonryItems?.forEach(item => {
    const element = item as HTMLElement;
    const innerElement = element.children[0];
    const innerElementHeight = innerElement.scrollHeight;

    element.style.gridRowEnd = `span ${Math.ceil(innerElementHeight / (elementGap + elementAutoRow))}`;
  });
}
