import { rest } from 'msw';

import { data } from './data';
import { API_URL } from '@/constants';

export const trimNPerformanceHandler = [
  rest.get(`${API_URL}/test/model/1/trim/2/n_performance`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data }));
  }),
];
