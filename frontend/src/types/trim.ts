import { OptionContextProps } from './option';

export interface FeatureProps {
  name: string;
  icon: string;
}
interface TrimProps {
  id: number;
  name: string;
  price: number;
  trimFeatures: FeatureProps[];
}
export interface TrimDataProps {
  carImageURL: string[];
  trims: TrimProps[];
}

interface EngineProps {
  id: number;
  name: string;
  additionalPrice: number;
  description: string;
  maximumPower: string;
  maximumTorque: string;
  imageURL: string;
}
export interface EngineDataProps {
  engines: EngineProps[];
}

interface BodyTypeProps {
  id: number;
  name: string;
  imageURLs: string[];
  additionalPrice: number;
  description: string;
}
export interface BodyTypeDataProps {
  bodyTypes: BodyTypeProps[];
}

interface WheelDriveProps {
  id: number;
  name: string;
  additionalPrice: number;
  description: string;
  imageURL: string;
}
export interface WheelDriveDataProps {
  wheelDrives: WheelDriveProps[];
}

interface MyCarTypeProps {
  krName: string;
  enName: string;
}

interface MyCarDetailProps {
  title: string;
  price: number;
}

export interface MyCarProps {
  carType: MyCarTypeProps;
  model: MyCarDetailProps;
  engine: MyCarDetailProps;
  bodyType: MyCarDetailProps;
  wheelDrive: MyCarDetailProps;
  outerColor: { title: string; imageUrl: string; price: number };
  innerColor: { title: string; imageUrl: string; id: number };
  options: OptionContextProps[];
  carImageUrl: string;
  [key: string]: any;
}

export interface MyCarLayoutContextProps {
  trim: MyCarProps;
  handleTrim: ({ key, option, price }: { key: string; option: string; price: number }) => void;
  handleOuterColor: ({ color, colorImage, price }: { color: string; colorImage: string; price: number }) => void;
  handleInnerColor: ({ color, colorImage, id }: { color: string; colorImage: string; id: number }) => void;
  totalPrice: number;
}
