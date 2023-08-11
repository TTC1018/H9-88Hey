export const OPTION_CARD_LIST_LENGTH = 6;

export enum ModalTypeProps {
  CLOSE = 'CLOSE',
  DELETE = 'DELETE',
  MOVE = 'MOVE',
}
export const TAG_CHIP_MAX_NUMBER = 3;
export const API_Url = import.meta.env.VITE_API_Url;

export const ARCHIVING = '아카이빙';
export const MY_ARCHIVING = '마이카이빙';

export const PATH_LIST = Object.freeze({
  archiving: ARCHIVING,
  'my-archiving': MY_ARCHIVING,
});
