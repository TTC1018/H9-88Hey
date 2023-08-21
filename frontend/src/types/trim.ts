import { Dispatch, MutableRefObject } from 'react';

import { OptionContextProps } from './option';
import { ExteriorColorsProps, InteriorColorsProps } from './color';
import { MyCarActionType } from '@/constants';

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
  totalPrice: number;
  dispatch: Dispatch<ActionType>;
  handleCarImageUrl: (carImageUrl: string) => void;
  clearHGenuineAccessories: () => void;
}

export interface CarCodeProps {
  carCode: string;
}

export type ActionType =
  | {
      type: MyCarActionType.TRIM;
      props: TrimBaseProps;
    }
  | { type: MyCarActionType.TRIM_OPTION; props: HandleTrimOptionProps }
  | { type: MyCarActionType.EXTERIOR_COLOR; props: ExteriorColorDataProps }
  | { type: MyCarActionType.INTERIOR_COLOR; props: InteriorColorDataProps }
  | { type: MyCarActionType.ADD_OPTION; props: OptionContextProps }
  | { type: MyCarActionType.REMOVE_OPTION; props: string }
  | { type: MyCarActionType.CAR_IMAGE_URL; props: string }
  | { type: MyCarActionType.SAVE_OPTION; props: MyCarProps }
  | { type: MyCarActionType.CLEAR_OPTION; props: OptionContextProps[] };

export interface TagDataProps {
  tags: string[];
}
