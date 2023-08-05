export function addCommasToPrice(price: number) {
  const priceWithCommas = price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
  const priceWithCommasString = `+${priceWithCommas}원`;
  return priceWithCommasString;
}

export function convertToTwoDigits(index: number) {
  return (index + 1).toString().padStart(2, '0');
}
