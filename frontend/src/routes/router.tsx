import { createBrowserRouter } from 'react-router-dom';

import { Trim } from '@/pages/trim';
import { MyCarLayout } from '@/components/layout/MyCarLayout';

export const router = createBrowserRouter([
  {
    path: '/trim',
    element: <MyCarLayout />,
    children: [
      {
        path: '',
        element: <Trim />,
      },
      {
        path: 'engine',
        element: <div>여기는 엔진</div>,
      },
      {
        path: 'body-type',
        element: <div>여기는 바디타입</div>,
      },
      {
        path: 'wheel-drive',
        element: <div>여기는 휠타입</div>,
      },
    ],
  },
  {
    path: '/color',
  },
  {
    path: '/option',
    children: [
      {
        path: 'h-genuine-accessories',
        element: <div>h genuine</div>,
      },
      {
        path: 'n-performance',
        element: <div>n- performance</div>,
      },
    ],
  },
  {
    path: '/result',
  },
]);
