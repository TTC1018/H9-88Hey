import { useNavigate } from 'react-router-dom';

interface Props<T> {
  state?: T;
  path: string;
}
export function useMyCarNavigate<T>({ path, state }: Props<T>) {
  const navigate = useNavigate();

  function handleNavigate() {
    if (state === undefined) {
      navigate(path);
    } else {
      navigate(path, {
        state: state,
      });
    }
  }

  return { handleNavigate };
}
