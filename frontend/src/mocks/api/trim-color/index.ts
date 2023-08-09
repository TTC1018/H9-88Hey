import { rest } from 'msw';

import { data } from './data';

export const trimColorHandler = [
  rest.get('/model/1/trim/2/color', (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
