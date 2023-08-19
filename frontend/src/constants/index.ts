export const OPTION_CARD_LIST_LENGTH = 6;

export enum ModalType {
  CLOSE = 'CLOSE',
  DELETE = 'DELETE',
  MOVE = 'MOVE',
  CLEAR = 'CLEAR',
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

export const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;

export const AUTH_ALERT_MESSAGE = {
  EMAIL_EMPTY: '이메일 주소가 필요합니다.',
  PASSWORD_EMPTY: '비밀번호가 필요합니다.',
  EMAIL_INVALID: '이메일 주소가 유효하지 않습니다.',
  ACCOUNT_INCORRECT: '이메일 또는 비밀번호가 잘못 입력되었습니다.',
};
