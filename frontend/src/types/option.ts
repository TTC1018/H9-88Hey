import { MyCarProps } from './trim';

export interface OptionDataProps {
  selectOptions: OptionProps[];
}

export interface OptionProps {
  isAvailable?: boolean;
  id: string;
  name: string;
  additionalPrice: number;
  imageUrl: string;
  tags: string[];
  subOptions: SubOptionProps[];
}

export interface SubOptionProps {
  id?: string;
  name: string;
  imageUrl: string;
  description: string;
}

export interface OptionCardDataProps {
  isAvailable?: boolean;
  id: string;
  index: number;
  name: string;
  additionalPrice: number;
  imageUrl: string;
  subOptionNames: string[];
}

export interface DefaultOptionDataProps {
  defaultOptions: DefaultOptionProps[];
}

export interface DefaultOptionProps {
  category: string;
  subOptions: SubOptionProps[];
}

export interface OptionContextProviderProps {
  myCar: MyCarProps;
  addOption: (option: OptionContextProps) => void;
  removeOption: (name: string) => void;
  clearHGenuineAccessories: () => void;
}

export interface OptionContextProps {
  id: string;
  name: string;
  price: number;
  imageUrl: string;
  subOptions: string[];
  path: string;
}
