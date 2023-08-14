import { rest } from 'msw';

import { data } from './data';
import { API_URL } from '@/constants';

export const archivingHandler = [
  rest.get(`${API_URL}/archiving?model=1&options=1&options=2`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
