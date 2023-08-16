import { MutableRefObject } from 'react';
import { OptionContextProps } from './option';

export interface FeatureProps {
  name: string;
  imageUrl: string;
}
interface TrimProps {
  id: number;
  name: string;
  price: number;
  trimFeatures: FeatureProps[];
}
export interface TrimDataProps {
  carImageUrls: string[];
  trims: TrimProps[];
}

interface EngineProps {
  id: number;
  name: string;
  additionalPrice: number;
  description: string;
  maximumPower: string;
  maximumTorque: string;
  imageUrl: string;
}
export interface EngineDataProps {
  engines: EngineProps[];
}

interface BodyTypeProps {
  id: number;
  name: string;
  imageUrl: string;
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
  imageUrl: string;
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

interface MyCarDetailPropsWithId extends MyCarDetailProps {
  id: number;
}

export interface MyCarProps {
  carType: MyCarTypeProps;
  trim: MyCarDetailPropsWithId;
  engine: MyCarDetailPropsWithId;
  bodyType: MyCarDetailPropsWithId;
  wheelDrive: MyCarDetailPropsWithId;
  outerColor: { title: string; imageUrl: string; price: number };
  innerColor: { title: string; imageUrl: string; id: number };
  options: OptionContextProps[];
  carImageUrl: string;
  [key: string]: any;
}

export interface MyCarLayoutContextProps {
  myCar: MyCarProps;
  carCode: MutableRefObject<string>;
  handleTrim: ({ key, option, price, id }: { key: string; option: string; price: number; id: number }) => void;
  handleOuterColor: ({ color, colorImage, price }: { color: string; colorImage: string; price: number }) => void;
  handleInnerColor: ({ color, colorImage, id }: { color: string; colorImage: string; id: number }) => void;
  totalPrice: number;
  handleCarImageUrl: (carImageUrl: string) => void;
}

export interface CarCodeProps {
  carCode: string;
}
