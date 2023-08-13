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
  carImageUrl: string[];
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
  imageUrls: string[];
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

interface MyCarDetailProps {
  title: string;
  price: number;
}

export interface MyCarProps {
  model: MyCarDetailProps;
  engine: MyCarDetailProps;
  bodyType: MyCarDetailProps;
  wheelDrive: MyCarDetailProps;
  outerColor: { title: string; imageUrl: string; price: number };
  innerColor: { title: string; imageUrl: string; id: number };
  carImageUrl: string;
  options: {
    name: string;
    price: number;
  }[];
}

export interface MyCarLayoutContextProps {
  handleTrim: ({ key, option, price }: { key: string; option: string; price: number }) => void;
  handleOuterColor: ({ color, colorImage, price }: { color: string; colorImage: string; price: number }) => void;
  handleInnerColor: ({ color, colorImage, id }: { color: string; colorImage: string; id: number }) => void;
  handleCarImageUrl: (carImageUrl: string) => void;
  trim: MyCarProps;
}
