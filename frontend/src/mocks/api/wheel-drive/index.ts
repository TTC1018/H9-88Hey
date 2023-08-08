import { rest } from 'msw';

import { data } from './data';

export const wheelDriveHandler = [
  rest.get('/model/1/wheel-drive', (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
