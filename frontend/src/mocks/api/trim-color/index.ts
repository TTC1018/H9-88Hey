import { rest } from 'msw';

import { data } from './data';

export const trimColorHandler = [
  rest.get('/model/palisade/trim/le_blanc/color', (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
