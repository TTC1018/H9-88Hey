export interface ExterierColorsProps {
  id: number;
  name: string;
  carImagePath: string;
  colorImageURL: string;
  additionalPrice: number;
  availableInteriorColors: number[];
  tags: string[];
}

export interface InterierColorsProps {
  id: number;
  name: string;
  carImageURL: string;
  colorImageURL: string;
  tags: string[];
}

export interface ColorDataProps {
  exterierColors: ExterierColorsProps[];
  interierColors: InterierColorsProps[];
}
