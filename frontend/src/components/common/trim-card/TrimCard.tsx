import * as style from './TrimCard.style';

interface TrimCardProps {
  isActivate: boolean;
  title: string;
  price: string;
  description: string;
  hasEngineInfo: boolean;
  power: string;
  torque: string;
}
export function TrimCard({ isActivate, title, price, description, hasEngineInfo, power, torque }: TrimCardProps) {
  return (
    <style.Container isActivate={isActivate}>
      <style.Header>
        <style.Title>{title}</style.Title>
        <style.Price>+{price}원</style.Price>
      </style.Header>
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
