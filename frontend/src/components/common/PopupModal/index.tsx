import { useNavigate } from 'react-router-dom';

import { useModalContext } from '@/hooks/useModalContext';

import * as Styled from './style';

export function PopupModal() {
  const { modalState, handleClose } = useModalContext();
  const navigate = useNavigate();

  const state = (function () {
    switch (modalState.modalType) {
      case 'CLOSE':
        return {
          text: '내 차 만들기 종료',
          isBig: true,
          callback: () => {
            navigate('/archiving');
            console.log(modalState.callbackData);
          },
          content: <CloseContent />,
        };
      case 'DELETE':
        return {
          text: '삭제',
          isBig: false,
          callback: () => {
            console.log(modalState.callbackData);
          },
          content: <DeleteContent name={modalState.ContentData!} />,
        };
      case 'MOVE':
        return {
          text: '내 차 만들기 이동',
          isBig: true,
          callback: () => {
            console.log(modalState.callbackData);
          },
          content: <MoveContent date={modalState.ContentData!} />,
        };
    }
  })();

  function handleConfirm() {
    handleClose();
    state?.callback();
  }

  return (
    <Styled.Container>
      {state?.content}
      <Styled.ButtonWrapper>
        <Styled.CancleButton isBig={state!.isBig} onClick={handleClose}>
          취소
        </Styled.CancleButton>
        <Styled.ConfirmButton isBig={state!.isBig} onClick={handleConfirm}>
          {state!.text}
        </Styled.ConfirmButton>
      </Styled.ButtonWrapper>
    </Styled.Container>
  );
}

interface MoveContentProps {
  date: string;
}
function MoveContent({ date }: MoveContentProps) {
  return (
    <Styled.TextWrapper>
      <Styled.Fragment>
        <Styled.Bold>{date}</Styled.Bold>
        <Styled.Text>에 임시저장된 파일이에요.</Styled.Text>
      </Styled.Fragment>
      <Styled.Text>계속해서 내 차 만들기를 하시겠어요?</Styled.Text>
    </Styled.TextWrapper>
  );
}

interface DeleteContentProps {
  name: string;
}
function DeleteContent({ name }: DeleteContentProps) {
  return (
    <Styled.TextWrapper>
      <Styled.Fragment>
        <Styled.Bold>{name}</Styled.Bold>
        <Styled.Text>을</Styled.Text>
      </Styled.Fragment>
      <Styled.Text>내가 만든 차량 목록에서 삭제하시겠어요?</Styled.Text>
    </Styled.TextWrapper>
  );
}

function CloseContent() {
  return (
    <Styled.TextWrapper>
      <Styled.Text>내 차 만들기를 그만하시겠어요?</Styled.Text>
      <Styled.Fragment>
        <Styled.Text>만들던 차량은</Styled.Text>
        <Styled.Bold>{` 아카이빙>내가 만든 차량 `}</Styled.Bold>
        <Styled.Text>에</Styled.Text>
      </Styled.Fragment>
      <Styled.Text>저장해둘게요</Styled.Text>
    </Styled.TextWrapper>
  );
}
