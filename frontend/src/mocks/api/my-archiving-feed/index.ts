import { rest } from 'msw';

import { data } from './data';
import { API_URL } from '@/constants';

export const feedMyChivingHandler = [
  rest.get(`${API_URL}/mychiving/feed`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data }));
  }),
];
