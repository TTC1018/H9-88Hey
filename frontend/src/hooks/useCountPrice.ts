import { useEffect, useState } from 'react';

interface Props {
  prevPrice: number;
  nextPrice: number;
}
const DURATION = 50;
export function useCountPrice({ prevPrice, nextPrice }: Props) {
  const [count, setCount] = useState(prevPrice);
  const isIncreasing = prevPrice < nextPrice;
  const isAnimationContinuing = isIncreasing ? count < nextPrice : count > nextPrice;
  const increment = ((nextPrice - prevPrice) / (DURATION / 1000)) * (1 / 60);

  useEffect(() => {
    let startTime: number;
    let count = prevPrice;

    function animate(timestamp: number) {
      !startTime && (startTime = timestamp);
      const elapsedTime = timestamp - startTime;

      if (elapsedTime < DURATION && isAnimationContinuing) {
        count += increment;
        setCount(count);
        requestId = requestAnimationFrame(animate);

        return;
      }

      setCount(nextPrice);
    }

    let requestId = requestAnimationFrame(animate);

    return function () {
      cancelAnimationFrame(requestId);
    };
  }, [increment, isAnimationContinuing, nextPrice, prevPrice, count]);

  return parseInt(count.toFixed(0));
}
