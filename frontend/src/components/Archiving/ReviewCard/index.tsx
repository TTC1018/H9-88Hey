import { MouseEvent } from 'react';

import { apiPath } from '@/constants';
import { useAuthMutation } from '@/hooks/useAuthMutation';
import { useMyCarNavigate } from '@/hooks/useMyCarNavigate';
import { ArchivingProps, BookmarkDataProps } from '@/types/archiving';
import { combineWithSlash, formatDate } from '@/utils';

import { OptionSelectCard } from '@/components/Archiving/OptionSelectCard';
import { XButton } from '@/components/MyChiving/XButton';

import * as Styled from './style';

interface ArchivingCardProps {
  isArchiving: true;
  selectedSearchOptions: Set<string>;
  onClick: (id: string, key: 'feedId' | 'myChivingId') => void;
}

interface MyChivingCardProps {
  isArchiving: false;
  selectedSearchOptions?: never[];
  onClick: (id: string, key: 'feedId' | 'myChivingId') => void;
}

type ChivingProps = ArchivingCardProps | MyChivingCardProps;

interface DefaultProps {
  props: ArchivingProps;
}

type Props = DefaultProps & ChivingProps;

export function ReviewCard({ props, isArchiving, selectedSearchOptions, onClick }: Props) {
  const {
    feedId,
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

  const { handleNavigate } = useMyCarNavigate({ path: `/archiving/detail?feed_id=${feedId}`, state: props });

  const { authMutation } = useAuthMutation<BookmarkDataProps, null>({
    url: apiPath.bookMark(feedId),
  });

  const dateText = `에 ${isPurchase ? '구매' : '시승'}했어요`;
  const trimOptions = combineWithSlash([engine.name, bodyType.name, wheelDrive.name]);

  return (
    <Styled.Contaienr onClick={handleNavigate}>
      <Styled.TitleWrapper>
        <Styled.Enclosure>
          <Styled.Title>{`${modelName} ${trim.name}`}</Styled.Title>
          <Styled.SubTitle>{trimOptions}</Styled.SubTitle>
        </Styled.Enclosure>
        <Styled.SideBox>
          <Styled.Time>
            {formatDate(creationDate)}
            {dateText}
          </Styled.Time>
          {!isArchiving && (
            <XButton
              onClick={(event: MouseEvent<HTMLButtonElement>) => {
                event.stopPropagation();
                onClick(feedId, 'feedId');
                authMutation({ method: 'DELETE' });
              }}
            />
          )}
        </Styled.SideBox>
      </Styled.TitleWrapper>
      <Styled.TextWrapper>
        <Styled.TextBox>
          <Styled.BodyText>외장</Styled.BodyText>
          <Styled.ColorText>{exteriorColor.name}</Styled.ColorText>
        </Styled.TextBox>
        <Styled.TextBox>
          <Styled.BodyText>내장</Styled.BodyText>
          <Styled.ColorText>{interiorColor.name}</Styled.ColorText>
        </Styled.TextBox>
      </Styled.TextWrapper>
      <Styled.OptionWrapper>
        <Styled.BodyText>선택옵션</Styled.BodyText>
        <Styled.OptionBox>
          {selectedOptions.map(({ name, id }) => (
            <Styled.Enclosure key={id}>
              <OptionSelectCard
                isArchiving={isArchiving}
                isActive={isArchiving ? selectedSearchOptions.has(id) : false}
                text={name}
              />
            </Styled.Enclosure>
          ))}
        </Styled.OptionBox>
      </Styled.OptionWrapper>
      <Styled.Description>{review}</Styled.Description>
      <Styled.TagWrapper>
        {tags.map((tag, index) => (
          <Styled.Tag key={`${tag} ${index}`}>{tag}</Styled.Tag>
        ))}
      </Styled.TagWrapper>
    </Styled.Contaienr>
  );
}
