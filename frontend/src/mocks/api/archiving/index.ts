import { rest } from 'msw';

import { data } from './data';
import { API_URL } from '@/constants';

export const archivingHandler = [
  rest.get(
    `${API_URL}/test/archiving?model_id=1&select_option=CO2&select_option=DUP&limit=8&offset=0`,
    (_, res, ctx) => {
      return res(ctx.json({ status: 200, message: '', data: data }));
    }
  ),
];
