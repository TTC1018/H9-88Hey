import { createBrowserRouter } from 'react-router-dom';

import { Trim } from '@/pages/Trim';
import { Color } from '@/pages/Color';
import { Review } from '@/pages/Review';
import { Result } from '@/pages/Result';
import { Option } from '@/pages/Option';
import { Engine } from '@/pages/Trim/Engine';
import { MyChiving } from '@/pages/MyChiving';
import { Archiving } from '@/pages/Archiving';
import { BodyType } from '@/pages/Trim/BodyType';
import { Detail } from '@/pages/Archiving/Detail';
import { WheelDrive } from '@/pages/Trim/WheelDrive';
import { PopupModal } from '@/components/common/PopupModal';
import { MyCarLayout } from '@/components/layout/MyCarLayout';
import { ModalPortal } from '@/components/common/ModalPortal';
import { ErrorBoundary } from '@/components/common/ErrorBoundary';
import { MyChivingLayout } from '@/components/layout/MyChivingLayout';

export const router = createBrowserRouter([
  {
    path: '',
    element: (
      <ErrorBoundary>
        <MyCarLayout />
        <ModalPortal>
          <PopupModal />
        </ModalPortal>
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
]);
