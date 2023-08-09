import { rest } from 'msw';

import { data } from './data';

export const trimHandler = [
  rest.get('/model/1/trim', (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
