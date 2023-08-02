import { createBrowserRouter } from 'react-router-dom';
import { Trim } from '@pages/trim';

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
      },
      {
        path: 'body-type',
      },
      {
        path: 'wheel-drive',
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
