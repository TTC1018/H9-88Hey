import { rest } from 'msw';

import { data } from './data';

export const engineHandler = [
  rest.get('/model/1/engine', (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
