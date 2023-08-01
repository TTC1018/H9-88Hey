const colors = {
  hyundaiPrimaryBlue: '#002C5F',
  hyundaiPrimaryBlue500: '#10498A',
  hyundaiPrimaryBlue600: '#00234C',
  hyundaiPrimaryBlue700: '#001A39',
  hyundaiPrimaryBlue800: '#001226',
  hyundaiPrimaryBlue900: '#000913',

  alertPrimary: '#FA6253',
  alertSecondary: '#FFD1CD',
  alertBrown: '#561C16',

  hyundaiSand: '#E4DCD3',
  hyundaiLightSand: '#F6F3F2',
  hyundaiGold: '#A36B4F',
  hyundaiBlue: '#4062DC',

  hyundaiNeutral: '#FAFAFA',
  lightGray: '#DCDCDC',
  mediumGray: '#BEBEBE',
  darkGray: '#545454',
  black: '#232323',
};

const fontWeight = {
  regular: 400,
  medium: 500,
  bold: 700,
};

const fontSize = {
  Microscopic: '10px',
  Tiny: '12px',
  Small: '14px',
  Normal: '16px',
  Large: '18px',
  Huge: '20px',
  Gigantic: '24px',
  Enormous: '26px',
};

const lineHeights = {
  Compact: '20px',
  Normal: '22px',
  Loose: '24px',
  Tight: '28px',
  Spacious: '32px',
  Expanded: '36px',
};

const fonts = {
  weight: fontWeight,
  size: fontSize,
  lineHeight: lineHeights,
};

export const theme = {
  colors,
  fonts,
};

export type ColorType = typeof colors;
export type FontType = typeof fonts;
