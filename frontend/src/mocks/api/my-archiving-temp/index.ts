import { rest } from 'msw';

import { data } from './data';
import { API_URL } from '@/constants';

export const tempMyChivingHandler = [
  rest.get(`${API_URL}/test/mychiving/temp`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data }));
  }),
];
