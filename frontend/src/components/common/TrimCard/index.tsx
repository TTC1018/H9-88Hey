import * as style from './style';

interface TrimCardProps {
  power: string;
  title: string;
  price: string;
  torque: string;
  isActivate: boolean;
  description: string;
  hasEngineInfo: boolean;
  onClick: () => void;
}
export function TrimCard({
  isActivate,
  title,
  price,
  description,
  hasEngineInfo,
  power,
  torque,
  onClick,
}: TrimCardProps) {
  return (
    <style.Container isActivate={isActivate} onClick={onClick}>
      <style.Wrapper>
        <style.Title>{title}</style.Title>
        <style.Price>+{price}원</style.Price>
      </style.Wrapper>
      <style.Description>{description}</style.Description>
      {hasEngineInfo && (
        <>
          <style.Line isActivate={isActivate} />
          <style.Info>
            <style.InfoTitle>최고출력</style.InfoTitle>
            <style.InfoContent>{power}</style.InfoContent>
          </style.Info>
          <style.Info>
            <style.InfoTitle>최대토크</style.InfoTitle>
            <style.InfoContent>{torque}</style.InfoContent>
          </style.Info>
        </>
      )}
    </style.Container>
  );
}
