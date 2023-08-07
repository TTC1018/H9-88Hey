export interface Feature {
  name: string;
  icon: string;
}
interface Trim {
  name: string;
  price: number;
  images: string[];
  features: Feature[];
}
export interface TrimDataType {
  trims: Trim[];
}

interface Engine {
  name: string;
  extraCharge: number;
  desc: string;
  maximumOutput: string;
  maximumTorque: string;
  image: string;
}
export interface EngineDataType {
  engines: Engine[];
}

interface BodyType {
  name: string;
  extraCharge: number;
  desc: string;
  images: string[];
}
export interface BodyTypeDataType {
  bodyTypes: BodyType[];
}

interface WheelDrive {
  name: string;
  extraCharge: number;
  desc: string;
  image: string;
}
export interface WheelDriveDataType {
  wheelDrive: WheelDrive[];
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
