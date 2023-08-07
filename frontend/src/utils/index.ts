import { OPTION_CARD_LIST_LENGTH } from '../constants';

// DEPRECATED: toLocaleString('en')을 대신 이용
export function addCommasToPrice(price: number) {
  const priceWithCommas = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
  const priceWithCommasString = `+${priceWithCommas}원`;
  return priceWithCommasString;
}

export function convertToTwoDigits(index: number) {
  return (index + 1).toString().padStart(2, '0');
}

export function isIndexLargeThanZero(index: number) {
  return index > 0;
}

export function isIndexSmallThanMaxIndex(index: number, length: number) {
  const maxIndex = Math.ceil(length / OPTION_CARD_LIST_LENGTH) - 1;
  return index < maxIndex;
}
