import { useEffect, useState } from 'react';

interface Props {
  scrollY: number;
  scrollTo: number;
}
export function useShowScrollButton({ scrollY, scrollTo }: Props) {
  const [isShow, setIsShow] = useState(false);

  function scrollToTop() {
    window.scroll({
      top: scrollTo,
      behavior: 'smooth',
    });
  }

  useEffect(() => {
    function showButtonClick() {
      if (window.scrollY > scrollY) {
        setIsShow(true);
      } else {
        setIsShow(false);
      }
    }
    window.addEventListener('scroll', showButtonClick);
    return () => {
      window.removeEventListener('scroll', showButtonClick);
    };
  }, []);

  return { isShow, scrollToTop };
}
