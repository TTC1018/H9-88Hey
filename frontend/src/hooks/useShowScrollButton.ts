import { useEffect, useState } from 'react';

interface Props {
  scrollY: number;
  scrollTo: number;
}
export function useShowScrollButton({ scrollY, scrollTo }: Props) {
  const [isShow, setIsShow] = useState(false);

  const scrollToTop = () => {
    window.scroll({
      top: scrollTo,
      behavior: 'smooth',
    });
  };

  useEffect(() => {
    const ShowButtonClick = () => {
      if (window.scrollY > scrollY) {
        setIsShow(true);
      } else {
        setIsShow(false);
      }
    };
    window.addEventListener('scroll', ShowButtonClick);
    return () => {
      window.removeEventListener('scroll', ShowButtonClick);
    };
  }, []);

  return { isShow, scrollToTop };
}
