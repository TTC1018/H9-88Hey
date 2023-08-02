import { createBrowserRouter } from 'react-router-dom';

export const router = createBrowserRouter([
  {
    path: '/trim',
    children: [
      {
        path: '/engine',
      },
      {
        path: '/body-type',
      },
      {
        path: '/wheel-drive',
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
        path: '/h-genuine-accessories',
      },
      {
        path: '/n-performance',
      },
    ],
  },
  {
    path: '/result',
  },
]);
