import { rest } from 'msw';

import { data } from './data';
import { API_URL } from '@/constants';

export const trimDefaultOptionHandler = [
  rest.get(`${API_URL}/model/1/trim/2/default_option`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
