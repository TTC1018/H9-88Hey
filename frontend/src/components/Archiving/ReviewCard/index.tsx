import { MouseEvent } from 'react';

import { ArchivingProps } from '@/types/archiving';
import { MyFeedProps } from '@/types/myChiving';
import { combineWithSlash, formatDate } from '@/utils';

import { OptionSelectCard } from '@/components/Archiving/OptionSelectCard';
import { XButton } from '@/components/MyChiving/XButton';

import * as style from './style';

interface ArchivingCardProps {
  isArchiving: true;
  selectedSearchOptions: Set<string>;
}

interface MyChivingCardProps {
  isArchiving: false;
  selectedSearchOptions?: never[];
}

type ChivingProps = ArchivingCardProps | MyChivingCardProps;

interface ClickEventDataProps {
  deleteText: string;
}

interface DefaultProps {
  props: ArchivingProps;
  onClick?: (review: MyFeedProps, data: ClickEventDataProps, event: MouseEvent<HTMLDivElement>) => void;
}

type Props = DefaultProps & ChivingProps;

export function ReviewCard({ props, isArchiving, onClick, selectedSearchOptions }: Props) {
  const {
    isPurchase,
    modelName,
    trim,
    engine,
    bodyType,
    wheelDrive,
    creationDate,
    exteriorColor,
    interiorColor,
    review,
    tags,
    selectedOptions,
  } = props;

  const dateText = ` ${isPurchase ? '구매' : '시승'}했어요`;

  function handleClick(event: MouseEvent<HTMLDivElement>) {
    if (onClick) {
      onClick(props, { deleteText: `${model} ${trim}` }, event);
    }
  }
  return (
    <style.Contaienr onClick={handleClick}>
      <style.TitleWrapper>
        <style.Enclosure>
          <style.Title>{`${modelName} ${trim.name}`}</style.Title>
          <style.SubTitle>{combineWithSlash([engine.name, bodyType.name, wheelDrive.name])}</style.SubTitle>
        </style.Enclosure>
        <style.SideBox>
          <style.Time>
            {formatDate(creationDate)}에 {dateText}
          </style.Time>
          {!isArchiving && <XButton />}
        </style.SideBox>
      </style.TitleWrapper>
      <style.TextWrapper>
        <style.TextBox>
          <style.BodyText>외장</style.BodyText>
          <style.ColorText>{exteriorColor.name}</style.ColorText>
        </style.TextBox>
        <style.TextBox>
          <style.BodyText>내장</style.BodyText>
          <style.ColorText>{interiorColor.name}</style.ColorText>
        </style.TextBox>
      </style.TextWrapper>
      <style.OptionWrapper>
        <style.BodyText>선택옵션</style.BodyText>
        <style.OptionBox>
          {selectedOptions.map(({ name }) => (
            <style.Enclosure key={name}>
              <OptionSelectCard
                isArchiving={isArchiving}
                isActive={isArchiving ? selectedSearchOptions.has(name) : false}
                text={name}
              />
            </style.Enclosure>
          ))}
        </style.OptionBox>
      </style.OptionWrapper>
      <style.Description>{review}</style.Description>
      <style.TagWrapper>
        {tags.map(tag => (
          <style.Tag key={tag}>{tag}</style.Tag>
        ))}
      </style.TagWrapper>
    </style.Contaienr>
  );
}
