import { rest } from 'msw';

import { data } from './data';
import { API_URL } from '@/constants';

export const trimColorHandler = [
  rest.get(`${API_URL}/test/car/color?trim_id=1`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data }));
  }),
];
