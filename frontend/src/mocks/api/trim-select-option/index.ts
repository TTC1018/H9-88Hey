import { rest } from 'msw';

import { data } from './data';
import { API_URL } from '@/constants';

export const trimSelectOptionHandler = [
  rest.get(`${API_URL}/test/car/select-option?car_code=LXJJ7DBT5`, (_, res, ctx) => {
    return res(ctx.json({ status: 200, message: '', data: data }));
  }),
];
