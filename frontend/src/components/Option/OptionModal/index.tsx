import Close from '/public/assets/icons/icon_close.svg';

import * as Styled from './style';

interface Props {
  name: string;
  imageUrl: string;
  description: string;
  onClick: () => void;
}

export function OptionModal({ name, imageUrl, description, onClick }: Props) {
  const processedDescription = description === '-' ? '' : description;

  return (
    <>
      <Styled.Container onClick={onClick} />
      <Styled.ModalWrapper>
        <Styled.TitleBox>
          <Styled.Title>{name}</Styled.Title>
          <Styled.Icon src={Close} onClick={onClick} />
        </Styled.TitleBox>
        <Styled.Line />
        <Styled.ContentBox>
          <Styled.ImageBox>
            <Styled.Image src={imageUrl} />
          </Styled.ImageBox>
          <Styled.DescriptionBox>
            <Styled.Description dangerouslySetInnerHTML={{ __html: processedDescription }}></Styled.Description>
          </Styled.DescriptionBox>
        </Styled.ContentBox>
        <Styled.ButtonBox>
          <Styled.Button onClick={onClick}>확인</Styled.Button>
        </Styled.ButtonBox>
      </Styled.ModalWrapper>
    </>
  );
}
