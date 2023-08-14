import { useOutletContext } from 'react-router-dom';

import { MyCarLayoutContextProps } from '@/types/trim';

import { Title } from '@/components/Result/Title';
import { TitleLine } from '@/components/Result/TitleLine';
import { Summary } from '@/components/Result/Summary';
import { OptionCardList } from '@/components/Result/OptionCardList';
import { MenuLine } from '@/components/Result/MenuLine';
import { Menu } from '@/components/Result/Menu';
import { Footer } from '@/components/Result/Footer';

import * as Styled from './style';

export function Result() {
  const { trim, totalPrice } = useOutletContext<MyCarLayoutContextProps>();
  const { krName, enName } = trim.carType;

  return (
    <Styled.Container>
      <Title krName={krName} enName={enName} />
      <TitleLine />
      <Summary trim={trim} />
      <OptionCardList options={trim.options} />
      <MenuLine />
      <Menu />
      <Footer totalPrice={totalPrice} />
    </Styled.Container>
  );
}
