import { createBrowserRouter } from 'react-router-dom';

import { Trim } from '@/pages/trim';
import { Engine } from '@/pages/trim/Engine';
import { BodyType } from '@/pages/trim/BodyType';
import { WheelDrive } from '@/pages/trim/WheelDrive';

export const router = createBrowserRouter([
  {
    path: '/trim',
    children: [
      {
        path: '',
        element: <Trim />,
      },
      {
        path: 'engine',
        element: <Engine />,
      },
      {
        path: 'body-type',
        element: <BodyType />,
      },
      {
        path: 'wheel-drive',
        element: <WheelDrive />,
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
      },
      {
        path: 'n-performance',
      },
    ],
  },
  {
    path: '/result',
  },
]);
