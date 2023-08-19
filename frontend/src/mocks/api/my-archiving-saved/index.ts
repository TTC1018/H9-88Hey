import { rest } from 'msw';

import { data } from './data';
import { API_URL } from '@/constants';

export const savedMyChivingHandler = [
  rest.get(`${API_URL}/test/mychiving`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data }));
  }),
];
