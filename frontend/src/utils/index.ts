import { OptionContextProps } from '@/types/option';
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
  return strings.filter(Boolean).join(' / ');
}

export function hasJongSeong(word: string) {
  const unicode = word.charCodeAt(word.length - 1);

  const hangeulStart = 0xac00;
  const hangeulEnd = 0xd7a3;

  if (unicode < hangeulStart || unicode > hangeulEnd) {
    return false;
  }

  return (unicode - hangeulStart) % 28 > 0;
}

export function getLocalStorage(key: string) {
  return localStorage.getItem(key)!;
}

export function setLocalStorage(key: string, value: string) {
  return localStorage.setItem(key, value);
}

export function checkIsOptionPage(path: string) {
  const splittedPath = path.split('/');

  return splittedPath[1] === 'option';
}

export function checkIsSelectOptionPage(path: string) {
  return path === '/option';
}

export function checkIsHGenuineAccessoriesPage(path: string) {
  return path === '/option/h-genuine-accessories';
}

export function checkIsNPerformancePage(path: string) {
  return path === '/option/n-performance';
}

export function checkIsResultPage(path: string) {
  return path === '/result';
}

export function isHGenuineAccessoriesSelected(options: OptionContextProps[]) {
  const hGenuineAccessories = options.filter(({ path }) => path === '/option/h-genuine-accessories');
  return hGenuineAccessories.length > 0;
}
