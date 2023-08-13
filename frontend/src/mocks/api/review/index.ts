import { rest } from 'msw';

import { data } from './data';
import { API_URL } from '@/constants';

export const engineHandler = [
  rest.get(`${API_URL}/review`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
