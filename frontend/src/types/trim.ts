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

interface MyCarDetailType {
  title: string;
  price: number;
}
export interface MyCarType {
  model: MyCarDetailType;
  engine: MyCarDetailType;
  bodyType: MyCarDetailType;
  wheelDrive: MyCarDetailType;
  color: {
    outer: [string, string];
    inner: [string, string];
  };
  options: string[];
}

export interface MyCarLayoutContextType {
  handleTrim: ({ key, option, price }: { key: string; option: string; price: number }) => void;
  trim: MyCarType;
}
