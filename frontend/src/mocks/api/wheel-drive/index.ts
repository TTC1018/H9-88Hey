import { rest } from 'msw';

import { data } from './data';
import { API_URL } from '@/constants';

export const wheelDriveHandler = [
  rest.get(`${API_URL}/test/car/model/1/wheel-drive`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data }));
  }),
];
