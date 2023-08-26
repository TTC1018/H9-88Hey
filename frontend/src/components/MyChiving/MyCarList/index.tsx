import { MouseEvent, MutableRefObject } from 'react';

import { formatDate } from '@/utils';
import { ModalType } from '@/constants';
import { MyChivingProps } from '@/types/myChiving';
import { useAuthMutation } from '@/hooks/useAuthMutation';

import { XButton } from '@/components/MyChiving/XButton';
import { ColorCircle } from '@/components/common/ColorCircle';

import * as Styled from './style';

interface ClickEventDataProps {
  deleteText: string;
  moveText: string;
}

interface MyCarListProps {
  myChiving: MyChivingProps;
  onClick: (myChiving: MyChivingProps, data: ClickEventDataProps, isSaved: boolean) => void;
  onClickDelete: (myChiving: MyChivingProps) => void;
  modalInfo: MutableRefObject<{
    type: ModalType;
    contents: string;
    onClick: () => void;
  }>;
  handleOpen: () => void;
}

export function MyCarList({ myChiving, onClick, onClickDelete, modalInfo, handleOpen }: MyCarListProps) {
  const {
    isSaved,
    model,
    trim,
    engine,
    bodyType,
    wheelDrive,
    lastModifiedDate,
    selectOptions,
    exteriorColor,
    interiorColor,
    myChivingId,
  } = myChiving;

  const date = formatDate(lastModifiedDate);
  const dateInfoText = isSaved ? `${date}에 만들었어요.` : `${date} 임시저장`;
  const trimOptions = `${engine?.name ? engine.name : ''}${bodyType?.name ? ` / ${bodyType.name}` : ''} ${
    wheelDrive?.name ? ` / ${wheelDrive.name}` : ''
  }`;

  const { authMutation } = useAuthMutation<string, null>({ url: `/mychiving/${myChivingId}` });

  // 여기에 post 추가
  function handleClickDelete(
    event: MouseEvent<HTMLButtonElement>,
    myChiving: MyChivingProps,
    data: ClickEventDataProps
  ) {
    event.stopPropagation();

    modalInfo.current = {
      type: ModalType.DELETE,
      contents: data.deleteText,
      onClick: () => {
        onClickDelete(myChiving);
        authMutation({ method: 'DELETE' });
      },
    };

    handleOpen();
  }

  return (
    <Styled.Container
      onClick={() => onClick(myChiving, { deleteText: `${model.name} ${trim.name}`, moveText: `${date}` }, isSaved)}
    >
      <Styled.Wrapper>
        <Styled.InfoBox>
          {!isSaved && <Styled.InfoText>저장하지 않고 나간 차량이 있어요.</Styled.InfoText>}
        </Styled.InfoBox>
        <Styled.MainBox>
          <Styled.InfoClosure>
            <Styled.Title>
              <Styled.TitleText>
                {model.name} {trim.name}
              </Styled.TitleText>
              <Styled.TrimText>{trimOptions}</Styled.TrimText>
            </Styled.Title>
            {exteriorColor && interiorColor && (
              <Styled.ColorBox>
                <ColorCircle imageUrl={exteriorColor?.colorImageUrl} />
                <Styled.ColorName>{exteriorColor.name}</Styled.ColorName>
                <ColorCircle imageUrl={interiorColor?.colorImageUrl} />
                <Styled.ColorName>{interiorColor.name}</Styled.ColorName>
              </Styled.ColorBox>
            )}
          </Styled.InfoClosure>
          <Styled.SubTitle>
            <Styled.SubTitleText isSaved={isSaved}>{dateInfoText}</Styled.SubTitleText>
            <XButton
              onClick={event =>
                handleClickDelete(event, myChiving, { deleteText: `${model.name} ${trim.name}`, moveText: `${date}` })
              }
            />
          </Styled.SubTitle>
        </Styled.MainBox>
        <Styled.OptionBox>
          {selectOptions && selectOptions.length > 0
            ? selectOptions.map((option, index) => (
                <Styled.OptionCard key={index} imageUrl={option.imageUrl}>
                  <Styled.OptionCardText>{option.name}</Styled.OptionCardText>
                </Styled.OptionCard>
              ))
            : [1, 2, 3, 4].map(item => (
                <Styled.EmptyOptionCard key={item}>
                  {item === 1 && (
                    <>
                      <Styled.Text>선택한 옵션이 </Styled.Text>
                      <Styled.Text>없습니다.</Styled.Text>
                    </>
                  )}
                </Styled.EmptyOptionCard>
              ))}
        </Styled.OptionBox>
      </Styled.Wrapper>
    </Styled.Container>
  );
}
