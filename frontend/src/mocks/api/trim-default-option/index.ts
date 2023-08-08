import { rest } from 'msw';

import { data } from './data';

export const trimDefaultOptionHandler = [
  rest.get('/model/palisade/trim/le_blanc/default_option', (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
