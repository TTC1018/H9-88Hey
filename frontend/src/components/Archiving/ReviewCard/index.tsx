import { ArchivingProps } from '@/types/archiving';
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
interface DefaultProps {
  props: ArchivingProps;
  onClick: (option: string) => void;
}
type Props = DefaultProps & ChivingProps;
export function ReviewCard({ props, isArchiving, onClick, selectedSearchOptions }: Props) {
  const {
    isPurchase,
    model,
    trim,
    trimOptions,
    creationDate,
    exteriorColor,
    interiorColor,
    review,
    tags,
    selectedOptions,
  } = props;

  const dateText = `에 ${isPurchase ? '구매' : '시승'}했어요`;

  return (
    <style.Contaienr>
      <style.TitleWrapper>
        <style.Enclosure>
          <style.Title>{`${model} ${trim}`}</style.Title>
          <style.SubTitle>{combineWithSlash(trimOptions)}</style.SubTitle>
        </style.Enclosure>
        <style.SideBox>
          <style.Time>
            {formatDate(creationDate)}에 {dateText}
          </style.Time>
          {!isArchiving && (
            <XButton
              onClick={() => {
                console.log(123);
              }}
            />
          )}
        </style.SideBox>
      </style.TitleWrapper>
      <style.TextWrapper>
        <style.TextBox>
          <style.BodyText>외장</style.BodyText>
          <style.ColorText>{exteriorColor}</style.ColorText>
        </style.TextBox>
        <style.TextBox>
          <style.BodyText>내장</style.BodyText>
          <style.ColorText>{interiorColor}</style.ColorText>
        </style.TextBox>
      </style.TextWrapper>
      <style.OptionWrapper>
        <style.BodyText>선택옵션</style.BodyText>
        <style.OptionBox>
          {selectedOptions.map(({ name }) => (
            <style.Enclosure key={name} onClick={() => onClick(name)}>
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
