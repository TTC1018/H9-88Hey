import { rest } from 'msw';

import { data } from './data';

export const bodyTypeHandler = [
  rest.get('/model/1/body-type', (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
