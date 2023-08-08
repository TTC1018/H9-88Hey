import { useModalContext } from '@/hooks/useModalContext';

import * as style from './style';

export function PopupModal() {
  const { modalState, handleClose } = useModalContext();

  const state = (function () {
    switch (modalState.modalType) {
      case 'CLOSE':
        return {
          text: '내 차 만들기 종료',
          isBig: true,
          callback: () => {
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
    <style.Container>
      {state?.content}
      <style.ButtonWrapper>
        <style.CancleButton isBig={state!.isBig} onClick={handleClose}>
          취소
        </style.CancleButton>
        <style.ConfirmButton isBig={state!.isBig} onClick={handleConfirm}>
          {state!.text}
        </style.ConfirmButton>
      </style.ButtonWrapper>
    </style.Container>
  );
}

interface MoveContentProps {
  date: string;
}
function MoveContent({ date }: MoveContentProps) {
  return (
    <style.TextWrapper>
      <style.Fragment>
        <style.Bold>{date}</style.Bold>
        <style.Text>에 임시저장된 파일이에요.</style.Text>
      </style.Fragment>
      <style.Text>계속해서 내 차 만들기를 하시겠어요?</style.Text>
    </style.TextWrapper>
  );
}

interface DeleteContentProps {
  name: string;
}
function DeleteContent({ name }: DeleteContentProps) {
  return (
    <style.TextWrapper>
      <style.Fragment>
        <style.Bold>{name}</style.Bold>
        <style.Text>을</style.Text>
      </style.Fragment>
      <style.Text>내가 만든 차량 목록에서 삭제하시겠어요?</style.Text>
    </style.TextWrapper>
  );
}

function CloseContent() {
  return (
    <style.TextWrapper>
      <style.Text>내 차 만들기를 그만하시겠어요?</style.Text>
      <style.Fragment>
        <style.Text>만들던 차량은</style.Text>
        <style.Bold>{` 아카이빙>내가 만든 차량 `}</style.Bold>
        <style.Text>에</style.Text>
      </style.Fragment>
      <style.Text>저장해둘게요</style.Text>
    </style.TextWrapper>
  );
}
