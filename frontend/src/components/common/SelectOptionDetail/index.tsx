import * as style from './style';

interface SelectOptionDetailProps {
  title: string;
}

export function SelectOptionDetail({ title }: SelectOptionDetailProps) {
  return (
    <>
      <style.Container>
        <style.TitleBox>{title}</style.TitleBox>
      </style.Container>
    </>
  );
}
