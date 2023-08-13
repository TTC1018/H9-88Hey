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

const lineHeight = {
  Compact: '20px',
  Normal: '22px',
  Loose: '24px',
  Tight: '28px',
  Spacious: '32px',
  Expanded: '36px',
};

const fonts = {
  headingBold1: {
    fontFamily: 'HyundaiSansBold',
    fontSize: fontSize.Enormous,
    fontStyle: 'normal',
    fontWeight: fontWeight.bold,
    lineHeight: lineHeight.Expanded,
    letterSpacing: '-0.52px',
  },
  headingMedium1: {
    fontFamily: 'HyundaiSansMedium',
    fontSize: fontSize.Enormous,
    fontStyle: 'normal',
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Expanded,
    letterSpacing: '-0.52px',
  },
  headingRegular1: {
    fontFamily: 'HyundaiSansRegular',
    fontSize: fontSize.Enormous,
    fontStyle: 'normal',
    fontWeight: fontWeight.regular,
    lineHeight: lineHeight.Expanded,
    letterSpacing: '-0.52px',
  },
  headingBold2: {
    fontFamily: 'HyundaiSansBold',
    fontSize: fontSize.Gigantic,
    fontStyle: 'normal',
    fontWeight: fontWeight.bold,
    lineHeight: lineHeight.Spacious,
    letterSpacing: '-0.48px',
  },
  headingMedium2: {
    fontFamily: 'HyundaiSansMedium',
    fontSize: fontSize.Gigantic,
    fontStyle: 'normal',
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Spacious,
    letterSpacing: '-0.48px',
  },
  headingBold3: {
    fontFamily: 'HyundaiSansBold',
    fontSize: fontSize.Huge,
    fontStyle: 'normal',
    fontWeight: fontWeight.bold,
    lineHeight: lineHeight.Tight,
    letterSpacing: '-0.4px',
  },
  headingMedium3: {
    fontFamily: 'HyundaiSansMedium',
    fontSize: fontSize.Huge,
    fontStyle: 'normal',
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Tight,
    letterSpacing: '-0.4px',
  },
  headingBold4: {
    fontFamily: 'HyundaiSansBold',
    fontSize: fontSize.Large,
    fontStyle: 'normal',
    fontWeight: fontWeight.bold,
    lineHeight: lineHeight.Loose,
    letterSpacing: '-0.36px',
  },
  headingMedium4: {
    fontFamily: 'HyundaiSansMedium',
    fontSize: fontSize.Large,
    fontStyle: 'normal',
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Loose,
    letterSpacing: '-0.36px',
  },

  bodyRegular1: {
    fontFamily: 'HyundaiSansRegular',
    fontSize: fontSize.Large,
    fontStyle: 'normal',
    fontWeight: fontWeight.regular,
    lineHeight: lineHeight.Loose,
    letterSpacing: '-0.36px',
  },
  bodyMedium1: {
    fontFamily: 'HyundaiSansMedium',
    fontSize: fontSize.Large,
    fontStyle: 'normal',
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Loose,
    letterSpacing: '-0.36px',
  },
  bodyRegular2: {
    fontFamily: 'HyundaiSansRegular',
    fontSize: fontSize.Normal,
    fontStyle: 'normal',
    fontWeight: fontWeight.regular,
    lineHeight: lineHeight.Loose,
    letterSpacing: '-0.32px',
  },
  bodyMedium2: {
    fontFamily: 'HyundaiSansMedium',
    fontSize: fontSize.Normal,
    fontStyle: 'normal',
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Loose,
    letterSpacing: '-0.32px',
  },
  bodyRegular3: {
    fontFamily: 'HyundaiSansRegular',
    fontSize: fontSize.Small,
    fontStyle: 'normal',
    fontWeight: fontWeight.regular,
    lineHeight: lineHeight.Normal,
    letterSpacing: '-0.28px',
  },
  bodyMedium3: {
    fontFamily: 'HyundaiSansMedium',
    fontSize: fontSize.Small,
    fontStyle: 'normal',
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Normal,
    letterSpacing: '-0.28px',
  },
  bodyRegular4: {
    fontFamily: 'HyundaiSansRegular',
    fontSize: fontSize.Tiny,
    fontStyle: 'normal',
    fontWeight: fontWeight.regular,
    lineHeight: lineHeight.Compact,
    letterSpacing: '-0.24px',
  },
  bodyMedium4: {
    fontFamily: 'HyundaiSansMedium',
    fontSize: fontSize.Tiny,
    fontStyle: 'normal',
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Compact,
    letterSpacing: '-0.24px',
  },

  captionRegular: {
    fontFamily: 'HyundaiSansRegular',
    fontSize: fontSize.Microscopic,
    fontStyle: 'normal',
    fontWeight: fontWeight.regular,
    lineHeight: lineHeight.Compact,
    letterSpacing: '-0.2px',
  },
  captionMedium: {
    fontFamily: 'HyundaiSansMedium',
    fontSize: fontSize.Microscopic,
    fontStyle: 'normal',
    fontWeight: fontWeight.medium,
    lineHeight: lineHeight.Compact,
    letterSpacing: '-0.2px',
  },
};

export const theme = {
  colors,
  fonts,
};

export type ColorType = typeof colors;
export type FontType = typeof fonts;
