import { Outlet, createBrowserRouter } from 'react-router-dom';

import { Signin } from '@/pages/Signin';
import { Signup } from '@/pages/Signup';
import { Trim } from '@/pages/Trim';
import { Color } from '@/pages/Color';
import { Option } from '@/pages/Option';
import { Engine } from '@/pages/Trim/Engine';
import { BodyType } from '@/pages/Trim/BodyType';
import { WheelDrive } from '@/pages/Trim/WheelDrive';
import { Review } from '@/pages/Review';
import { Result } from '@/pages/Result';
import { MyChiving } from '@/pages/MyChiving';
import { Archiving } from '@/pages/Archiving';
import { Detail } from '@/pages/Archiving/Detail';
import { ErrorBoundary } from '@/components/common/ErrorBoundary';
import { MyCarLayout } from '@/components/layout/MyCarLayout';
import { MyChivingLayout } from '@/components/layout/MyChivingLayout';

export const router = createBrowserRouter([
  {
    path: '/',
    element: (
      <ErrorBoundary>
        <Outlet />
      </ErrorBoundary>
    ),
    children: [
      {
        index: true,
        element: <Signin />,
      },
      {
        path: 'signup',
        element: <Signup />,
      },
      {
        path: '',
        element: <MyCarLayout />,
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
            path: 'color',
            element: <Color />,
          },
          {
            path: 'option',
            children: [
              {
                path: '',
                element: <Option key="option" apiType="select-option" />,
              },
              {
                path: 'h-genuine-accessories',
                element: <Option key="h-genuine-accessories" apiType="h-genuine-accessories" />,
              },
              {
                path: 'n-performance',
                element: <Option key="n-performance" apiType="n-performance" />,
              },
            ],
          },
          {
            path: 'result',
            element: <Result />,
          },
        ],
      },
      {
        path: '',
        element: <MyChivingLayout />,
        children: [
          {
            path: 'archiving',
            children: [
              {
                path: '',
                element: <Archiving />,
              },
              {
                path: 'detail',
                element: <Detail />,
              },
            ],
          },
          {
            path: 'mychiving',
            element: <MyChiving />,
          },
        ],
      },
      {
        path: 'review',
        element: <Review />,
      },
    ],
  },
]);
