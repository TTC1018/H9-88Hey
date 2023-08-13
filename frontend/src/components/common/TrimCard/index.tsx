import * as Styled from './style';

interface TrimCardPropsWithEngineInfo {
  title: string;
  price: number;
  isActive: boolean;
  description: string;
  hasEngineInfo: true;
  power: string;
  torque: string;
}
interface TrimCardPropsWithoutEngineInfo {
  title: string;
  price: number;
  isActive: boolean;
  description: string;
  hasEngineInfo: false;
  power?: never;
  torque?: never;
}
type TrimCardProps = TrimCardPropsWithEngineInfo | TrimCardPropsWithoutEngineInfo;
export function TrimCard({ isActive, title, price, description, hasEngineInfo, power, torque }: TrimCardProps) {
  return (
    <Styled.Container isActive={isActive}>
      <Styled.Wrapper>
        <Styled.Title>{title}</Styled.Title>
        <Styled.Price>+{price.toLocaleString()}원</Styled.Price>
      </Styled.Wrapper>
      <Styled.Description>{description}</Styled.Description>
      {hasEngineInfo && (
        <>
          <Styled.Line isActive={isActive} />
          <Styled.Info>
            <Styled.InfoTitle>최고출력</Styled.InfoTitle>
            <Styled.InfoContent>{power}</Styled.InfoContent>
          </Styled.Info>
          <Styled.Info>
            <Styled.InfoTitle>최대토크</Styled.InfoTitle>
            <Styled.InfoContent>{torque}</Styled.InfoContent>
          </Styled.Info>
        </>
      )}
    </Styled.Container>
  );
}
