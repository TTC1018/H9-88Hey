import { rest } from 'msw';

import { data } from './data';
import { API_URL } from '@/constants';

export const archivingCarHandler = [
  rest.get(`${API_URL}/test/archiving/cars`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
