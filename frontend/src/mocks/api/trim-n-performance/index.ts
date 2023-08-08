import { rest } from 'msw';

import { data } from './data';

export const trimNPerformanceHandler = [
  rest.get('/model/palisade/trim/le_blanc/n_perfomance', (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
