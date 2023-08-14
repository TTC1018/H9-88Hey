import { rest } from 'msw';

import { data } from './data';
import { API_URL } from '@/constants';

export const bodyTypeHandler = [
  rest.get(`${API_URL}/model/1/body-type`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
