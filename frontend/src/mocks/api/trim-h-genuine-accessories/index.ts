import { rest } from 'msw';

import { data } from './data';

export const trimHGenuineAccessoriesHandler = [
  rest.get('/model/palisade/trim/le_blanc/h_genuine_accessories', (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
