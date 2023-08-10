export interface ExterierColorsProps {
  id: number;
  name: string;
  carImagePath: string;
  colorImageUrl: string;
  additionalPrice: number;
  availableInteriorColors: number[];
  tags: string[];
}

export interface InterierColorsProps {
  id: number;
  name: string;
  carImageUrl: string;
  colorImageUrl: string;
  tags: string[];
}

export interface ColorDataProps {
  exterierColors: ExterierColorsProps[];
  interierColors: InterierColorsProps[];
}
