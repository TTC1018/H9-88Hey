import { createBrowserRouter } from 'react-router-dom';

import { Trim } from '@/pages/Trim';
import { Engine } from '@/pages/Trim/Engine';
import { BodyType } from '@/pages/Trim/BodyType';
import { WheelDrive } from '@/pages/Trim/WheelDrive';
import { Option } from '@/pages/Option';
import { MyCarLayout } from '@/components/layout/MyCarLayout';
import { Color } from '@/pages/Color';

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
            element: <Option key="option" apiType="select_option" />,
          },
          {
            path: 'h-genuine-accessories',
            element: <Option key="h-genuine-accessories" apiType="h_genuine_accessories" />,
          },
          {
            path: 'n-performance',
            element: <Option key="n-performance" apiType="n_performance" />,
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
