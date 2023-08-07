import { rest } from 'msw';

import { data } from './data';

export const bodyTypeHandler = [
  rest.get('/model/palisade/trim/le_blanc/body_type', (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
