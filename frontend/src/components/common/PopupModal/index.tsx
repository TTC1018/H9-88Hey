import * as style from './style';

type Modaltype = 'create' | 'remove' | 'close';
const MODAL_CONTENT = {
  create: {
    text: '내 차 만들기 이동',
    isBig: true,
  },
  close: {
    text: '내 차 만들기 종료',
    isBig: true,
  },
  remove: {
    text: '삭제',
    isBig: false,
  },
};

interface PopupModalProps {
  type: Modaltype;
  onCancel: () => void;
  onConfirm: () => void;
}

export function PopupModal({ type, onCancel, onConfirm }: PopupModalProps) {
  const { text, isBig } = MODAL_CONTENT[type];

  return (
    <style.Container>
      {/* <CloseContent /> */}
      <RemoveContent name="palicase" />
      <style.ButtonWrapper>
        <style.CancleButton isBig={isBig} onClick={onCancel}>
          취소
        </style.CancleButton>
        <style.ConfirmButton isBig={isBig} onClick={onConfirm}>
          {text}
        </style.ConfirmButton>
      </style.ButtonWrapper>
    </style.Container>
  );
}

interface CreateContentProps {
  date: string;
}
function CreateContent({ date }: CreateContentProps) {
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

interface RemoveContentProps {
  name: string;
}
function RemoveContent({ name }: RemoveContentProps) {
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
