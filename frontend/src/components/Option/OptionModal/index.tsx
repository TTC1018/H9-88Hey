import * as style from './style';

interface Props {
  name: string;
  imageUrl: string;
  description: string;
  onClick: () => void;
}

export function OptionModal({ name, imageUrl, description, onClick }: Props) {
  return (
    <>
      <style.Container onClick={onClick} />
      <style.ModalWrapper>
        <style.TitleBox>
          <style.Title>{name}</style.Title>
          <style.Icon src="/src/assets/icons/icon_close.svg" onClick={onClick} />
        </style.TitleBox>
        <style.Line />
        <style.ContentBox>
          <style.ImageBox>
            <style.Image src={imageUrl} />
          </style.ImageBox>
          <style.DescriptionBox>
            <style.Description>{description}</style.Description>
          </style.DescriptionBox>
        </style.ContentBox>
        <style.ButtonBox>
          <style.Button onClick={onClick}>확인</style.Button>
        </style.ButtonBox>
      </style.ModalWrapper>
    </>
  );
}
