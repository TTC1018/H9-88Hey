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

export function hasJongSeong(word: string) {
  const unicode = word.charCodeAt(word.length - 1);

  const hangeulStart = 0xac00;
  const hangeulEnd = 0xd7a3;

  if (unicode < hangeulStart || unicode > hangeulEnd) {
    return false;
  }

  return (unicode - hangeulStart) % 28 > 0;
}
