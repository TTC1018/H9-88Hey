import { Outlet } from 'react-router-dom';

import { Header } from '@/components/common/Header';
import { Navigation } from '@/components/common/Navigation';
import { Footer } from '@/components/common/Footer';

import * as style from './style';

export function MyCarLayout() {
  return (
    <style.Container>
      <Header />
      <Navigation />
      <style.Wrapper>
        <Outlet />
      </style.Wrapper>
      <Footer />
    </style.Container>
  );
}
