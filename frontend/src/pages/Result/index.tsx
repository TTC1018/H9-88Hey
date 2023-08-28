import { useLocation } from 'react-router-dom';

import { MyCarProps } from '@/types/trim';
import { AuthError } from '@/utils/AuthError';

import { Title } from '@/components/Result/Title';
import { TitleLine } from '@/components/Result/TitleLine';
import { Summary } from '@/components/Result/Summary';
import { OptionCardList } from '@/components/Result/OptionCardList';
import { MenuLine } from '@/components/Result/MenuLine';
import { Menu } from '@/components/Result/Menu';
import { Footer } from '@/components/Result/Footer';

import * as Styled from './style';

export function Result() {
  const { state } = useLocation();

  const myCar = state as MyCarProps;
  const myCarKeysWithPrice = ['engine', 'bodyType', 'wheelDrive', 'exteriorColor'];

  if (myCar === null) {
    throw new AuthError('로그아웃 상태에서 결과 페이지 접근', 400);
  }

  const totalPrice =
    myCar.trim.price +
    myCarKeysWithPrice.reduce((acc, cur) => acc + myCar[cur].additionalPrice, 0) +
    myCar.options.reduce((acc, cur) => acc + cur.additionalPrice, 0);

  const { krName, enName } = myCar.carType;

  return (
    <Styled.Container>
      <Title krName={krName} enName={enName} imageUrl={myCar.carImageUrl} />
      <TitleLine />
      <Summary myCar={myCar} />
      <OptionCardList options={myCar.options} />
      <MenuLine />
      <Menu />
      <Footer totalPrice={totalPrice} />
    </Styled.Container>
  );
}
