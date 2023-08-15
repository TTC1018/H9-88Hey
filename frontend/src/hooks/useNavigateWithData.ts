import { useNavigate } from 'react-router-dom';

interface Props<T> {
  state: T;
}
export function useNavigateWithData({ path }: { path: string }) {
  const navigate = useNavigate();

  function naviagteWithData<T>({ state }: Props<T>) {
    navigate(path, {
      state: state,
    });
  }

  return { naviagteWithData };
}
