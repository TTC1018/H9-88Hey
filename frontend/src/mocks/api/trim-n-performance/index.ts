import { rest } from 'msw';

import { data } from './data';
import { API_Url } from '@/constants';

export const trimNPerformanceHandler = [
  rest.get(`${API_Url}/model/palisade/trim/le_blanc/n_perfomance`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
