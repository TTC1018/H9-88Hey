import { createBrowserRouter } from 'react-router-dom';

import { Trim } from '@/pages/Trim';
import { Color } from '@/pages/Color';
import { Option } from '@/pages/Option';
import { Engine } from '@/pages/Trim/Engine';
import { Archiving } from '@/pages/Archiving';
import { BodyType } from '@/pages/Trim/BodyType';
import { WheelDrive } from '@/pages/Trim/WheelDrive';
import { PopupModal } from '@/components/common/PopupModal';
import { MyCarLayout } from '@/components/layout/MyCarLayout';
import { ModalPortal } from '@/components/common/ModalPortal';
import { ErrorBoundary } from '@/components/common/ErrorBoundary';
import { Detail } from '@/pages/Archiving/Detail';

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
    path: '/archiving',
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
]);
