export const OPTION_CARD_LIST_LENGTH = 6;

export enum ModalType {
  CLOSE = 'CLOSE',
  DELETE = 'DELETE',
  MOVE = 'MOVE',
}
export const TAG_CHIP_MAX_NUMBER = 3;
export const API_URL = import.meta.env.VITE_API_URL;
export const NAVIGATION_PATH = {
  '/trim': { prev: '', next: '/trim/engine' },
  '/trim/engine': { prev: '/trim', next: '/trim/body-type' },
  '/trim/body-type': { prev: '/trim/engine', next: '/trim/wheel-drive' },
  '/trim/wheel-drive': { prev: '/trim/body-type', next: 'color' },
  '/color': { prev: '/trim/wheel-drive', next: '/option' },
  '/option': { prev: '/color', next: '/option/h-genuine-accessories' },
  '/option/h-genuine-accessories': { prev: '/option', next: '/option/n-performance' },
  '/option/n-performance': { prev: '/option/h-genuine-accessories', next: '' },
};

export const ARCHIVING = '아카이빙';
export const MY_ARCHIVING = '마이카이빙';

export const PATH_LIST = Object.freeze({
  archiving: ARCHIVING,
  mychiving: MY_ARCHIVING,
});

export const MAX_TEXT_LENGTH = 140;
