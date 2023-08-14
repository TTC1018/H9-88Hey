import { OPTION_CARD_LIST_LENGTH } from '@/constants';

export function convertToTwoDigits(index: number) {
  return (index + 1).toString().padStart(2, '0');
}

export function isValidIndex(index: number, maxIndex: number) {
  return index >= 0 && index <= maxIndex;
}

export function isIndexLargeThanZero(index: number) {
  return index > 0;
}

export function isIndexSmallThanMaxIndex(index: number, length: number) {
  const maxIndex = Math.ceil(length / OPTION_CARD_LIST_LENGTH) - 1;
  return index < maxIndex;
}

export function formatDate(inputDate: string) {
  const date = new Date(inputDate);
  const year = date.getFullYear() % 100;
  const month = date.getMonth() + 1;
  const day = date.getDate();

  return `${year}년 ${month}월 ${day}일`;
}

export function combineWithSlash(strings: string[]) {
  return strings.join(' / ');
}
