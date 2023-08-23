import { rest } from 'msw';

import { data } from './data';
import { API_URL } from '@/constants';

export const savedMyChivingHandler = [
  rest.get(`${API_URL}/mychiving?user_id=1234&offset=1&limit=16`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data }));
  }),
];
