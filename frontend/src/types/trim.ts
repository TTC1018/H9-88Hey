export interface TrimOptionType {
  title: string;
  price: number;
  carImages: string[];
  optionImages: string[];
}

export interface BodyTypeOptionType {
  title: string;
  price: number;
  description: string;
  images: string[];
}

export interface WheelDriveOptionType {
  title: string;
  price: number;
  description: string;
  image: string;
}

export interface EngineOptionType {
  title: string;
  price: number;
  description: string;
  power: string;
  torque: string;
  image: string;
}
