import { DependencyList, EffectCallback, useEffect, useRef } from 'react';

export function useDidMountEffect(effect: EffectCallback, deps?: DependencyList | undefined) {
  const didMount = useRef(false);

  useEffect(() => {
    if (didMount.current) {
      effect();
    } else {
      didMount.current = true;
    }
  }, deps);
}
