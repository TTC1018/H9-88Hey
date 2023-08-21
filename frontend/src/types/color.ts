export interface ExteriorColorsProps {
  id: number;
  name: string;
  carImagePath: string;
  colorImageUrl: string;
  additionalPrice: number;
  availableInteriorColors: number[];
}

export interface InteriorColorsProps {
  id: number;
  name: string;
  carImageUrl: string;
  colorImageUrl: string;
}

export interface ColorDataProps {
  exteriorColors: ExteriorColorsProps[];
  interiorColors: InteriorColorsProps[];
}
