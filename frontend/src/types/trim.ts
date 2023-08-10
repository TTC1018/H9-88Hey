export interface FeatureType {
  name: string;
  icon: string;
}
interface TrimType {
  name: string;
  price: number;
  images: string[];
  features: FeatureType[];
}
export interface TrimDataType {
  trims: TrimType[];
}

interface EngineType {
  name: string;
  extraCharge: number;
  desc: string;
  maximumOutput: string;
  maximumTorque: string;
  image: string;
}
export interface EngineDataType {
  engines: EngineType[];
}

interface BodyTypeType {
  name: string;
  extraCharge: number;
  desc: string;
  images: string[];
}
export interface BodyTypeDataType {
  bodyTypes: BodyTypeType[];
}

interface WheelDriveType {
  name: string;
  extraCharge: number;
  desc: string;
  image: string;
}
export interface WheelDriveDataType {
  wheelDrive: WheelDriveType[];
}

interface MyCarDetailType {
  title: string;
  price: number;
}
export interface MyCarType {
  [key: string]: any;
  model: MyCarDetailType;
  engine: MyCarDetailType;
  bodyType: MyCarDetailType;
  wheelDrive: MyCarDetailType;
  color: {
    outer: [string, string];
    inner: [string, string];
  };
  options: {
    name: string;
    price: number;
  }[];
}

export interface MyCarLayoutContextType {
  handleTrim: ({ key, option, price }: { key: string; option: string; price: number }) => void;
  trim: MyCarType;
}
