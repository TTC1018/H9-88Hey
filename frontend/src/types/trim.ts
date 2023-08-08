export interface FeatureProps {
  name: string;
  icon: string;
}
interface TrimProps {
  name: string;
  price: number;
  images: string[];
  features: FeatureProps[];
}
export interface TrimDataProps {
  trims: TrimProps[];
}

interface EngineProps {
  name: string;
  extraCharge: number;
  desc: string;
  maximumOutput: string;
  maximumTorque: string;
  image: string;
}
export interface EngineDataProps {
  engines: EngineProps[];
}

interface BodyProps {
  name: string;
  extraCharge: number;
  desc: string;
  images: string[];
}
export interface BodyPropsDataProps {
  bodyPropss: BodyProps[];
}

interface WheelDriveProps {
  name: string;
  extraCharge: number;
  desc: string;
  image: string;
}
export interface WheelDriveDataProps {
  wheelDrive: WheelDriveProps[];
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
  color: {
    outer: [string, string];
    inner: [string, string];
  };
  options: string[];
}

export interface MyCarLayoutContextProps {
  handleTrim: ({ key, option, price }: { key: string; option: string; price: number }) => void;
  trim: MyCarProps;
}
