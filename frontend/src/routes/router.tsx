import { createBrowserRouter } from 'react-router-dom';

import { Trim } from '@/pages/Trim';
import { Color } from '@/pages/Color';
import { Option } from '@/pages/Option';
import { Engine } from '@/pages/Trim/Engine';
import { BodyType } from '@/pages/Trim/BodyType';
import { WheelDrive } from '@/pages/Trim/WheelDrive';
import { MyCarLayout } from '@/components/layout/MyCarLayout';
import { ErrorBoundary } from '@/components/common/ErrorBoundary';
import { MyArchivingLayout } from '@/components/layout/MyArchivingLayout';
import { Review } from '@/pages/Review';
import { Result } from '@/pages/Result';

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
      {
        path: '/result',
        element: <Result />,
      },
    ],
  },
  {
    path: 'my-archiving',
    element: <MyArchivingLayout />,
  },
  {
    path: 'archiving',
    element: <MyArchivingLayout />,
  },
  {
    path: 'review',
    element: <Review />,
  },
]);
