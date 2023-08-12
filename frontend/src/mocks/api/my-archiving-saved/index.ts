import { rest } from 'msw';

import { data } from './data';
import { API_Url } from '@/constants';

export const savedMyChivingHandler = [
  rest.get(`${API_Url}/mychiving`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
