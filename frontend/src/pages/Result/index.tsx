import { useLocation } from 'react-router-dom';

import { Title } from '@/components/Result/Title';
import { TitleLine } from '@/components/Result/TitleLine';
import { Summary } from '@/components/Result/Summary';
import { OptionCardList } from '@/components/Result/OptionCardList';
import { MenuLine } from '@/components/Result/MenuLine';
import { Menu } from '@/components/Result/Menu';
import { Footer } from '@/components/Result/Footer';

import * as Styled from './style';

export function Result() {
  const { state: myCar } = useLocation();

  const { krName, enName } = myCar.carType;

  return (
    <Styled.Container>
      <Title krName={krName} enName={enName} imageUrl={myCar.carImageUrl} />
      <TitleLine />
      <Summary myCar={myCar} />
      <OptionCardList options={myCar.options} />
      <MenuLine />
      <Menu />
      <Footer totalPrice={1000000} />
    </Styled.Container>
  );
}
