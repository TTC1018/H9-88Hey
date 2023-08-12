import { rest } from 'msw';

import { data } from './data';
import { API_Url } from '@/constants';

export const tempMyChivingHandler = [
  rest.get(`${API_Url}/mychiving/temp`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
