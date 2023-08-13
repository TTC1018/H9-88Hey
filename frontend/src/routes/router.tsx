import { createBrowserRouter } from 'react-router-dom';

import { Trim } from '@/pages/Trim';
import { Color } from '@/pages/Color';
import { Option } from '@/pages/Option';
import { Engine } from '@/pages/Trim/Engine';
import { BodyType } from '@/pages/Trim/BodyType';
import { WheelDrive } from '@/pages/Trim/WheelDrive';
import { MyCarLayout } from '@/components/layout/MyCarLayout';
import { ErrorBoundary } from '@/components/common/ErrorBoundary';
import { Review } from '@/pages/Review';

export const router = createBrowserRouter([
  {
    path: '',
    element: (
      <ErrorBoundary>
        <MyCarLayout />
      </ErrorBoundary>
    ),
    children: [
      {
        path: 'trim',
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
    path: 'review',
    element: <Review />,
  },
]);
