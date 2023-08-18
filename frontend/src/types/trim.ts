import { MutableRefObject } from 'react';
import { OptionContextProps } from './option';
import { ExteriorColorsProps, InteriorColorsProps } from './color';

export interface FeatureProps {
  name: string;
  imageUrl: string;
}
export interface TrimBaseProps {
  id: number;
  name: string;
  price: number;
}
interface TrimProps extends TrimBaseProps {
  trimFeatures: FeatureProps[];
}
export interface TrimDataProps {
  carImageUrls: string[];
  trims: TrimProps[];
}

interface TrimOptionBaseProps {
  id: number;
  name: string;
  additionalPrice: number;
}
interface TrimOptionProps extends TrimOptionBaseProps {
  imageUrl: string;
  description: string;
}
interface EngineProps extends TrimOptionProps {
  maximumPower: string;
  maximumTorque: string;
}

export interface EngineDataProps {
  engines: EngineProps[];
}

export interface BodyTypeDataProps {
  bodyTypes: TrimOptionProps[];
}

export interface WheelDriveDataProps {
  wheelDrives: TrimOptionProps[];
}

interface MyCarTypeProps {
  krName: string;
  enName: string;
}

export type ExteriorColorDataProps = Pick<ExteriorColorsProps, 'name' | 'colorImageUrl' | 'additionalPrice'>;
export type InteriorColorDataProps = Pick<InteriorColorsProps, 'name' | 'colorImageUrl' | 'id'>;

export interface MyCarProps {
  carType: MyCarTypeProps;
  trim: TrimBaseProps;
  engine: TrimOptionBaseProps;
  bodyType: TrimOptionBaseProps;
  wheelDrive: TrimOptionBaseProps;
  exteriorColor: ExteriorColorDataProps;
  interiorColor: InteriorColorDataProps;
  options: OptionContextProps[];
  carImageUrl: string;
  [key: string]: any;
}

export type HandleTrimOptionProps = TrimOptionBaseProps & { key: 'engine' | 'wheelDrive' | 'bodyType' };
export interface MyCarLayoutContextProps {
  myCar: MyCarProps;
  carCode: MutableRefObject<string>;
  handleTrim: (props: TrimBaseProps) => void;
  handleTrimOption: (props: HandleTrimOptionProps) => void;
  handleExteriorColor: (props: ExteriorColorDataProps) => void;
  handleInteriorColor: (props: InteriorColorDataProps) => void;
  totalPrice: number;
  handleCarImageUrl: (carImageUrl: string) => void;
}

export interface CarCodeProps {
  carCode: string;
}

export enum MyCarActionTypeProps {
  'TRIM' = 'TRIM',
  'TRIM_OPTION' = 'TRIM_OPTION',
  'EXTERIOR_COLOR' = 'EXTERIOR_COLOR',
  'INTERIOR_COLOR' = 'INTERIOR_COLOR',
  'ADD_OPTION' = 'ADD_OPTION',
  'REMOVE_OPTION' = 'REMOVE_OPTION',
  'SAVE_OPTION' = 'SAVE_OPTION',
  'CAR_IMAGE_URL' = 'CAR_IMAGE_URL',
}
