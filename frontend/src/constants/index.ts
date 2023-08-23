export const OPTION_CARD_LIST_LENGTH = 6;

export enum ModalType {
  CLOSE = 'CLOSE',
  DELETE = 'DELETE',
  MOVE = 'MOVE',
  CLEAR = 'CLEAR',
}
export const TAG_CHIP_MAX_NUMBER = 3;
export const API_URL = '/dev';
export const NAVIGATION_PATH = {
  '/trim': { prev: '', next: '/trim/engine' },
  '/trim/engine': { prev: '/trim', next: '/trim/body-type' },
  '/trim/body-type': { prev: '/trim/engine', next: '/trim/wheel-drive' },
  '/trim/wheel-drive': { prev: '/trim/body-type', next: 'color' },
  '/color': { prev: '/trim/wheel-drive', next: '/option' },
  '/option': { prev: '/color', next: '/option/h-genuine-accessories' },
  '/option/h-genuine-accessories': { prev: '/option', next: '/option/n-performance' },
  '/option/n-performance': { prev: '/option/h-genuine-accessories', next: '/result' },
  '/result': { prev: '', next: '' },
};

export const ARCHIVING = '아카이빙';
export const MY_ARCHIVING = '마이카이빙';

export const PATH_LIST = Object.freeze({
  archiving: ARCHIVING,
  mychiving: MY_ARCHIVING,
});

export const MAX_TEXT_LENGTH = 140;

export enum MyCarActionType {
  'TRIM' = 'TRIM',
  'TRIM_OPTION' = 'TRIM_OPTION',
  'EXTERIOR_COLOR' = 'EXTERIOR_COLOR',
  'INTERIOR_COLOR' = 'INTERIOR_COLOR',
  'ADD_OPTION' = 'ADD_OPTION',
  'REMOVE_OPTION' = 'REMOVE_OPTION',
  'SAVE_OPTION' = 'SAVE_OPTION',
  'CAR_IMAGE_URL' = 'CAR_IMAGE_URL',
  'CLEAR_OPTION' = 'CLEAR_OPTION',
  'CLEAR_COLORS' = 'CLEAR_COLORS',
}

const routePath = {
  trim: () => '/trim',
  engine: () => `${routePath.trim()}/engine`,
  bodyType: () => `${routePath.trim()}/body-type`,
  wheelDrive: () => `${routePath.trim()}/wheel-drive`,
  color: () => '/color',
  option: () => '/option',
  hGenuineAccessories: () => `${routePath.option()}/h-genuine-accessories`,
  nPerformance: () => `${routePath.option()}/n-performance`,
  result: () => '/result',
  archiving: () => '/archiving',
  detail: () => `${routePath.archiving()}/detail`,
  mychiving: () => '/mychiving',
};

export const apiPath = {
  carBase: () => '/car',
  trimBase: () => `${apiPath.carBase()}/model`,
  trim: (modelId: number) => `${apiPath.trimBase()}/${modelId}/trim`,
  image: (modelId: number) => `${apiPath.trimBase()}/${modelId}/image`,
  engine: (modelId: number) => `${apiPath.trimBase()}/${modelId}/engine`,
  bodyType: (modelId: number) => `${apiPath.trimBase()}/${modelId}/body-type`,
  wheelDrive: (modelId: number) => `${apiPath.trimBase()}/${modelId}/wheel-drive`,
  color: (trimId: number) => `${apiPath.carBase()}/color?trim_id=${trimId}`,
  carCode: (trimId: number, engineId: number, bodyTypeId: number, wheelDriveId: number) =>
    `${apiPath.carBase()}/car-code?trim_id=${trimId}&engine_id=${engineId}&body_type_id=${bodyTypeId}&wheel_drive_id=${wheelDriveId}`,
  option: (routePath: string, search: string) => `${apiPath.carBase()}/${routePath}${search}`,
  archivingBase: () => '/archiving',
  archiving: (modelId: number, selectOptionsId: string[], limit: number, offset: number) => {
    const optionQuerys = selectOptionsId.map(selectOptionsId => `&select_option=${selectOptionsId}`).join('');
    return `${apiPath.archivingBase()}?model_id=${modelId}${optionQuerys}&limit=${limit}&offset=${offset}`;
  },
  archivingOption: (modelId: number) => `${apiPath.carBase()}/select-options?model_id=${modelId}`,
  archivingDetail: (id: string) => `${apiPath.archivingBase()}/${id}`,
  tag: (type: string, id: string | number, limit: number) => `${apiPath.carBase()}/tag/${type}?id=${id}&limit=${limit}`,
  bookMark: (id: string) => `/user/archiving/${id}/bookmark`,
};

export const cacheKey = {
  trim: (modelId: number) => ['trim', `${modelId}`],
  image: (modelId: number) => ['image', `${modelId}`],
  engine: (modelId: number) => ['engine', `${modelId}`],
  bodyType: (modelId: number) => ['bodyType', `${modelId}`],
  wheelDrive: (modelId: number) => ['wheelDrive', `${modelId}`],
  color: (trimId: number) => ['color', `${trimId}`],
  carCode: (trimId: number, engineId: number, bodyTypeId: number, wheelDriveId: number) => [
    'carCode',
    `${trimId}`,
    `${engineId}`,
    `${bodyTypeId}`,
    `${wheelDriveId}`,
  ],
  option: (routePath: string, search: string) => ['option', `${routePath}`, `${search}`],
  archivingOption: (modelId: number) => ['select-options', `${modelId}`],
  archivingDetail: (id: string) => ['detail', `${id}`],
  tag: (type: string, id: string | number) => ['tag', `${type}`, `${id}`],
};
