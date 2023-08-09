import * as style from './style';

interface Props {
  name: string;
  imageURL: string;
  onClick: () => void;
}

export function OptionModal({ name, imageURL, onClick }: Props) {
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
            <style.Image src={imageURL} />
          </style.ImageBox>
          <style.DescriptionBox>
            <style.Description>
              신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는
              시스템입니다.
            </style.Description>
          </style.DescriptionBox>
        </style.ContentBox>
        <style.ButtonBox>
          <style.Button onClick={onClick}>확인</style.Button>
        </style.ButtonBox>
      </style.ModalWrapper>
    </>
  );
}
