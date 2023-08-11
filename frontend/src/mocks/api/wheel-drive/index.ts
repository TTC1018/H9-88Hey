import { rest } from 'msw';

import { data } from './data';
import { API_Url } from '@/constants';

export const wheelDriveHandler = [
  rest.get(`${API_Url}/model/1/wheel-drive`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
