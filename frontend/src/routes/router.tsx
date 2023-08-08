import { createBrowserRouter } from 'react-router-dom';

import { Trim } from '@/pages/Trim';
import { Engine } from '@/pages/Trim/Engine';
import { BodyType } from '@/pages/Trim/BodyType';
import { WheelDrive } from '@/pages/Trim/WheelDrive';
import { Option } from '@/pages/Option';
import { MyCarLayout } from '@/components/layout/MyCarLayout';
import { Color } from '@/pages/color';

export const router = createBrowserRouter([
  {
    path: '',
    element: <MyCarLayout />,
    children: [
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
        element: <Color />,
      },
      {
        path: '/option',
        children: [
          {
            path: '',
            element: <Option key="option" />,
          },
          {
            path: 'h-genuine-accessories',
            element: <Option key="h-genuine-accessories" />,
          },
          {
            path: 'n-performance',
            element: <Option key="n-performance" />,
          },
        ],
      },
    ],
  },
  {
    path: '*',
    element: <div>아직 안만듬</div>,
  },
]);
