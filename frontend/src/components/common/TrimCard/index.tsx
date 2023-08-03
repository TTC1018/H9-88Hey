import * as style from './style';

interface TrimCardPropsWithEngineInfo {
  title: string;
  price: number;
  isActive: boolean;
  description: string;
  onClick: () => void;
  hasEngineInfo: true;
  power: string;
  torque: string;
}
interface TrimCardPropsWithoutEngineInfo {
  title: string;
  price: number;
  isActive: boolean;
  description: string;
  onClick: () => void;
  hasEngineInfo: false;
  power?: never;
  torque?: never;
}
type TrimCardProps = TrimCardPropsWithEngineInfo | TrimCardPropsWithoutEngineInfo;
export function TrimCard({
  isActive,
  title,
  price,
  description,
  hasEngineInfo,
  power,
  torque,
  onClick,
}: TrimCardProps) {
  return (
    <style.Container isActive={isActive} onClick={onClick}>
      <style.Wrapper>
        <style.Title>{title}</style.Title>
        <style.Price>+{price}원</style.Price>
      </style.Wrapper>
      <style.Description>{description}</style.Description>
      {hasEngineInfo && (
        <>
          <style.Line isActive={isActive} />
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
