import * as Styled from './style';

interface Props {
  name: string;
  imageUrl: string;
  onClick: () => void;
}

export function OptionModal({ name, imageUrl, onClick }: Props) {
  return (
    <>
      <Styled.Container onClick={onClick} />
      <Styled.ModalWrapper>
        <Styled.TitleBox>
          <Styled.Title>{name}</Styled.Title>
          <Styled.Icon src="/src/assets/icons/icon_close.svg" onClick={onClick} />
        </Styled.TitleBox>
        <Styled.Line />
        <Styled.ContentBox>
          <Styled.ImageBox>
            <Styled.Image src={imageUrl} />
          </Styled.ImageBox>
          <Styled.DescriptionBox>
            <Styled.Description>
              신호 대기 상황이거나 정차 중일 때 차의 엔진을 일시 정지하여 연비를 향상시키고, 배출가스 발생을 억제하는
              시스템입니다.
            </Styled.Description>
          </Styled.DescriptionBox>
        </Styled.ContentBox>
        <Styled.ButtonBox>
          <Styled.Button onClick={onClick}>확인</Styled.Button>
        </Styled.ButtonBox>
      </Styled.ModalWrapper>
    </>
  );
}
