import { MyCarProps } from '@/types/trim';

import * as style from './style';

interface Props {
  onClick: () => void;
  myCarData: MyCarProps;
  totalPrice: number;
}
export function EstimateModal({ onClick, myCarData, totalPrice }: Props) {
  const {
    model,
    engine,
    bodyType,
    wheelDrive,
    // color, options
  } = myCarData;

  const trim = `${engine.title}${bodyType.title !== '' ? '/' : ''}${bodyType.title}${
    wheelDrive.title !== '' ? '/' : ''
  }${wheelDrive.title}`;
  const trimPrice = engine.price + bodyType.price + wheelDrive.price;

  return (
    <>
      <style.Container onClick={onClick} />
      <style.ModalContainer>
        <style.Header>견적요약보기</style.Header>

        <style.TitleWrapper>
          <style.Title>총 견적 금액</style.Title>
          <style.Price>{totalPrice.toLocaleString()} 원</style.Price>
        </style.TitleWrapper>

        <style.DescriptionWrapper>
          <style.DescriptionBox>
            <style.Description>{model.title}</style.Description>
            <style.Description>{model.price.toLocaleString()} 원</style.Description>
          </style.DescriptionBox>
          <style.DescriptionBox>
            <style.Description>{trim}</style.Description>
            <style.Description>+{trimPrice.toLocaleString()} 원</style.Description>
          </style.DescriptionBox>
        </style.DescriptionWrapper>

        <style.TitleWrapper>
          <style.Title>색상</style.Title>
        </style.TitleWrapper>

        <style.DescriptionWrapper>
          <style.DescriptionBox>
            <style.Title>외장</style.Title>
          </style.DescriptionBox>
          <style.DescriptionBox>
            <style.Title>내장</style.Title>
          </style.DescriptionBox>
        </style.DescriptionWrapper>

        <style.TitleWrapper>
          <style.Title>선택 옵션</style.Title>
        </style.TitleWrapper>

        <style.DescriptionWrapper>
          <style.DescriptionBox>
            <style.Description>컴포트 ||</style.Description>
            <style.Description>+1,090,000 원</style.Description>
          </style.DescriptionBox>
          <style.DescriptionBox>
            <style.Description>컴포트 ||</style.Description>
            <style.Description>+1,090,000 원</style.Description>
          </style.DescriptionBox>
          <style.DescriptionBox>
            <style.Description>컴포트 ||</style.Description>
            <style.Description>+1,090,000 원</style.Description>
          </style.DescriptionBox>
          <style.DescriptionBox>
            <style.Description>컴포트 ||</style.Description>
            <style.Description>+1,090,000 원</style.Description>
          </style.DescriptionBox>
          <style.DescriptionBox>
            <style.Description>컴포트 ||</style.Description>
            <style.Description>+1,090,000 원</style.Description>
          </style.DescriptionBox>
        </style.DescriptionWrapper>
      </style.ModalContainer>
    </>
  );
}
