import { OptionSelectCard } from '@/components/archiving/OptionSelectCard';

import * as style from './style';
import { DeleteButton } from '@/components/common/DeleteButton';

interface ArchivingProps {
  isArchiving: true;
  selectedOptions: Set<string>;
}
interface MyChivingProps {
  isArchiving: false;
  selectedOptions?: never[];
}
type ChivingProps = ArchivingProps | MyChivingProps;
interface DefaultProps {
  model: string;
  trim: string;
  outerColor: string;
  innerColor: string;
  options: string[];
  date: string;
  description: string;
  tags: string[];
}
type Props = DefaultProps & ChivingProps;
export function ReviewCard({
  model,
  trim,
  outerColor,
  innerColor,
  options,
  selectedOptions,
  date,
  description,
  tags,
  isArchiving,
}: Props) {
  return (
    <style.Contaienr>
      <style.TitleWrapper>
        <style.Enclosure>
          <style.Title>{model}</style.Title>
          <style.SubTitle>{trim}</style.SubTitle>
        </style.Enclosure>
        <style.SideBox>
          <style.Time>{date}</style.Time>
          {!isArchiving && <DeleteButton />}
        </style.SideBox>
      </style.TitleWrapper>
      <style.TextWrapper>
        <style.TextBox>
          <style.BodyText>외장</style.BodyText>
          <style.ColorText>{outerColor}</style.ColorText>
        </style.TextBox>
        <style.TextBox>
          <style.BodyText>내장</style.BodyText>
          <style.ColorText>{innerColor}</style.ColorText>
        </style.TextBox>
      </style.TextWrapper>
      <style.OptionWrapper>
        <style.BodyText>선택옵션</style.BodyText>
        <style.OptionBox>
          {options.map(option => (
            <OptionSelectCard
              key={option}
              isArchiving={isArchiving}
              isActive={isArchiving ? selectedOptions.has(option) : false}
              text={option}
            />
          ))}
        </style.OptionBox>
      </style.OptionWrapper>
      <style.Description>{description}</style.Description>
      <style.TagWrapper>
        {tags.map(tag => (
          <style.Tag key={tag}>{tag}</style.Tag>
        ))}
      </style.TagWrapper>
    </style.Contaienr>
  );
}
