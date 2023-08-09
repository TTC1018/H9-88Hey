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
  model: MyCarDetailType;
  engine: MyCarDetailType;
  bodyType: MyCarDetailType;
  wheelDrive: MyCarDetailType;
  outerColor: { title: string; imageUrl: string; price: number };
  innerColor: { title: string; imageUrl: string; id: number };
  options: string[];
}

export interface MyCarLayoutContextType {
  handleTrim: ({ key, option, price }: { key: string; option: string; price: number }) => void;
  handleOuterColor: ({ color, colorImage, price }: { color: string; colorImage: string; price: number }) => void;
  handleInnerColor: ({ color, colorImage, id }: { color: string; colorImage: string; id: number }) => void;
  trim: MyCarType;
}
