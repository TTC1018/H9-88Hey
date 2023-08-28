import * as style from './style';

interface Props {
  isActive: boolean;
  isArchiving: boolean;
  text: string;
}
export function OptionSelectCard({ isActive, isArchiving, text }: Props) {
  return (
    <style.Container isActive={isActive} isArchiving={isArchiving}>
      {text}
    </style.Container>
  );
}
