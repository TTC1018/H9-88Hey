import { Title } from '@/components/Result/Title';
import { TitleLine } from '@/components/Result/TitleLine';
import { Summary } from '@/components/Result/Summary';
import { OptionCardList } from '@/components/Result/OptionCardList';
import { MenuLine } from '@/components/Result/MenuLine';
import { Menu } from '@/components/Result/Menu';
import { Footer } from '@/components/Result/Footer';

import * as Styled from './style';

export function Result() {
  return (
    <Styled.Container>
      <Title />
      <TitleLine />
      <Summary />
      <OptionCardList />
      <MenuLine />
      <Menu />
      <Footer />
    </Styled.Container>
  );
}
